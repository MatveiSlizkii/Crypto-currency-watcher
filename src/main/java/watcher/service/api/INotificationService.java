package watcher.service.api;

import watcher.model.dto.Notification;

import java.util.List;
import java.util.UUID;

public interface INotificationService {
    Notification add (Notification notification);
    boolean updateEnable(UUID id);
    boolean checkAndUpdateEnable (UUID id, Double newPrice);
    List<Notification> usersByEnableAndSymbol (Boolean enable, String symbol);

}
