package watcher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import watcher.model.dto.Crypto;
import watcher.model.dto.Notification;
import watcher.service.api.ICryptoService;
import watcher.service.api.ISchedulerService;
import watcher.service.api.INotificationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulerService implements ISchedulerService {
    private final ICryptoService cryptoService;
    private final RestTemplate restTemplate;
    private final INotificationService userService;
    private final Logger logger = LoggerFactory.getLogger(SchedulerService.class);



    @Value("${coin_url}")
    private String urlCoin;

    public SchedulerService(ICryptoService cryptoService, INotificationService userService) {
        this.cryptoService = cryptoService;
        this.restTemplate = new RestTemplate();
        this.userService = userService;
    }

    @Scheduled(fixedRate = 60 * 1000)
    @Async
    @Transactional
    @Override
    public void updateAllPrices() {
        List<Crypto> cryptoList = cryptoService.getAllList();
        for (Crypto crypto : cryptoList) {
            //проверяем пользователей
            List<Notification> notificationList = userService.usersByEnableAndSymbol(true, crypto.getSymbol());
            notificationList.forEach((o) -> userService.checkAndUpdateEnable(o.getId(), crypto.getPriceUSD()));
            //обновляем цены на криптовалюту
            Double newPrice = this.getPrice(crypto.getId());
            cryptoService.updateCryptoPrice(crypto.getId(), newPrice);
            this.logger.info("{}: Обновление цены для монеты {}", LocalDateTime.now(), crypto.getSymbol());
        }

    }

    private Double getPrice(Long id) {
        Crypto[] cryptoInArray = this.restTemplate.getForObject(this.urlCoin + id, Crypto[].class);
        return cryptoInArray[0].getPriceUSD();
    }

}
