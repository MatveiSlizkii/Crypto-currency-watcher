package watcher.dao.converters;

import org.springframework.core.convert.converter.Converter;
import watcher.dao.api.ICryptoPriceStorage;
import watcher.dao.entity.CryptoEntity;
import watcher.model.dto.Crypto;

public class CryptoEntityToCryptoConverter implements Converter<CryptoEntity, Crypto> {
    private final ICryptoPriceStorage cryptoPriceStorage;

    public CryptoEntityToCryptoConverter(ICryptoPriceStorage cryptoPriceStorage) {
        this.cryptoPriceStorage = cryptoPriceStorage;
    }

    @Override
    public Crypto convert(CryptoEntity source) {
        Crypto crypto = new Crypto();
        crypto.setId(source.getId());
        crypto.setSymbol(source.getSymbol());
        crypto.setPriceUSD(cryptoPriceStorage.getById(source.getId()).getPriceUSD());

        crypto.setDtCreate(source.getDtCreate());
        crypto.setDtUpdate(source.getDtUpdate());
        return crypto;
    }

    @Override
    public <U> Converter<CryptoEntity, U> andThen(Converter<? super Crypto, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
