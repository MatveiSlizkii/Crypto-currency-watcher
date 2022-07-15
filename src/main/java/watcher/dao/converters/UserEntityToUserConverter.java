package watcher.dao.converters;

import org.springframework.core.convert.converter.Converter;
import watcher.dao.entity.NotificationEntity;
import watcher.model.dto.Notification;

public class UserEntityToUserConverter implements Converter<NotificationEntity, Notification> {

    @Override
    public Notification convert(NotificationEntity source) {
        return Notification.Builder.createBuilder()
                .setId(source.getId())
                .setUsername(source.getUsername())
                .setSymbol(source.getSymbol())
                .setStartPrice(source.getStartPrice())
                .setEnable(source.isEnable())
                .setDtCreate(source.getDtCreate())
                .setDtUpdate(source.getDtUpdate())
                .build();
    }

    @Override
    public <U> Converter<NotificationEntity, U> andThen(Converter<? super Notification, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
