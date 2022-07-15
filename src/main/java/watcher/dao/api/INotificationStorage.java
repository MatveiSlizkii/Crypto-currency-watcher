package watcher.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watcher.dao.entity.NotificationEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface INotificationStorage extends JpaRepository<NotificationEntity, UUID> {
    List<NotificationEntity> findAllByEnableAndSymbol (Boolean enable, String symbol);
    boolean existsUserEntityByUsername(String username);

}
