package watcher.dao.converters;

import org.springframework.core.convert.converter.Converter;
import watcher.dao.entity.NotificationEntity;
import watcher.model.dto.Notification;

public class UserToUserEntityConverter implements Converter<Notification, NotificationEntity> {
    @Override
    public NotificationEntity convert(Notification source) {
        return NotificationEntity.Builder.createBuilder()
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
    public <U> Converter<Notification, U> andThen(Converter<? super NotificationEntity, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
