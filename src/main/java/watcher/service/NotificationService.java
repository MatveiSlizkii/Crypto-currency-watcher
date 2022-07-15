package watcher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import watcher.dao.api.INotificationStorage;
import watcher.dao.entity.NotificationEntity;
import watcher.model.dto.Crypto;
import watcher.model.dto.Notification;
import watcher.service.api.ICryptoService;
import watcher.service.api.INotificationService;
import watcher.service.exception.EntityNotFound;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService implements INotificationService {
    private final ConversionService cs;
    private final INotificationStorage notificationStorage;
    private final ICryptoService cryptoService;
    private final EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(SchedulerService.class);



    public NotificationService(ConversionService cs, INotificationStorage notificationStorage,
                               ICryptoService cryptoService, EntityManager em) {
        this.cs = cs;
        this.notificationStorage = notificationStorage;
        this.cryptoService = cryptoService;
        this.em = em;
    }

    @Override
    public Notification add(Notification notification) {
        //порверка на юзернейм
        if (notificationStorage.existsUserEntityByUsername(notification.getUsername())) {
            throw new EntityNotFound("Данное имя пользователя уже занято");
        }
        //проверка на переданную монету (ексепшн внутри)
        cryptoService.checkSymbol(notification.getSymbol());

        notification.setId(UUID.randomUUID());
        LocalDateTime ldtn = LocalDateTime.now();
        notification.setDtCreate(ldtn);
        notification.setDtUpdate(ldtn);
        notificationStorage.save(cs.convert(notification, NotificationEntity.class));
        Crypto crypto = cryptoService.getCryptoPrice(notification.getSymbol());
        notification.setStartPrice(crypto.getPriceUSD());
        return notification;
    }

    @Override
    public boolean updateEnable(UUID id) {
        NotificationEntity notificationEntity = em.find(NotificationEntity.class, id);
        em.refresh(notificationEntity, LockModeType.OPTIMISTIC);
        notificationEntity.setEnable(false);
        return true;
    }

    @Transactional
    @Override
    public boolean checkAndUpdateEnable(UUID id, Double newPrice) {
        NotificationEntity notificationEntity = notificationStorage.getById(id);
        Double startPrice = notificationEntity.getStartPrice();
        double percentChange = ((newPrice / startPrice) - 1) * 100;
        if (percentChange >= 1){
            this.logger.warn( "{} {} {}", notificationEntity.getSymbol(), notificationEntity.getUsername(), percentChange);
            this.updateEnable(id);
        }
            return true;
    }

    @Override
    public List<Notification> usersByEnableAndSymbol(Boolean enable, String symbol) {
        List<NotificationEntity> userEntities = notificationStorage.findAllByEnableAndSymbol(enable, symbol);
        List<Notification> notificationList = new ArrayList<>();
        userEntities.forEach((o)-> notificationList.add(cs.convert(o, Notification.class)));
        return notificationList;
    }


}
