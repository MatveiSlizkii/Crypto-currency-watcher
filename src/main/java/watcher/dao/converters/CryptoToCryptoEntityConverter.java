package watcher.dao.converters;

import org.springframework.core.convert.converter.Converter;
import watcher.dao.entity.CryptoEntity;
import watcher.model.dto.Crypto;

public class CryptoToCryptoEntityConverter implements Converter<Crypto, CryptoEntity> {
    @Override
    public CryptoEntity convert(Crypto source) {
        CryptoEntity cryptoEntity = new CryptoEntity();
        cryptoEntity.setId(source.getId());
        cryptoEntity.setSymbol(source.getSymbol());

        cryptoEntity.setDtCreate(source.getDtCreate());
        cryptoEntity.setDtUpdate(source.getDtUpdate());
        return cryptoEntity;
    }

    @Override
    public <U> Converter<Crypto, U> andThen(Converter<? super CryptoEntity, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
