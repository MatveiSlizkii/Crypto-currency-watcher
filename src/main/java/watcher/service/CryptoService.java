package watcher.service;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import watcher.dao.api.ICryptoStorage;
import watcher.dao.api.ICryptoPriceStorage;
import watcher.dao.entity.CryptoEntity;
import watcher.dao.entity.CryptoPriceEntity;
import watcher.model.dto.Crypto;
import watcher.service.api.ICryptoService;
import watcher.service.exception.EntityNotFound;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoService implements ICryptoService {
    private final ICryptoStorage cryptoStorage;
    private final ICryptoPriceStorage cryptoPriceStorage;
    private final ConversionService cs;
    private final EntityManager em;

    public CryptoService(ICryptoStorage cryptoStorage,
                         ICryptoPriceStorage cryptoPriceStorage,
                         ConversionService cs, EntityManager em) {
        this.cryptoStorage = cryptoStorage;
        this.cryptoPriceStorage = cryptoPriceStorage;
        this.cs = cs;
        this.em = em;
    }

    @Override
    public Crypto getCryptoPrice(String symbol) {

        Optional<CryptoEntity> cryptoEntityOpt = Optional.ofNullable(cryptoStorage.findBySymbol(symbol)
                .orElseThrow(() -> new EntityNotFound("Передан несуществующий код валюты - " + symbol)));
        CryptoEntity cryptoEntity = cryptoEntityOpt.get();
        CryptoPriceEntity cryptoPriceEntity = cryptoPriceStorage.getById(cryptoEntity.getId());
        Crypto crypto = cs.convert(cryptoEntity, Crypto.class);
        crypto.setPriceUSD(cryptoPriceEntity.getPriceUSD());
        return crypto;
    }

    @Override
    public Page<Crypto> getAllPage(Pageable pageable) {
        List<CryptoEntity> cryptoEntities = cryptoStorage.findAll();
        List<Crypto> cryptoList = new ArrayList<>();
        cryptoEntities.forEach((o)-> cryptoList.add(cs.convert(o, Crypto.class)));
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), cryptoList.size());
        return new PageImpl<>(cryptoList.subList(start, end), pageable, cryptoList.size());
    }

    @Override
    public List<Crypto> getAllList() {
        List<CryptoEntity> cryptoEntities = cryptoStorage.findAll();
        List<Crypto> cryptoList = new ArrayList<>();
        cryptoEntities.forEach((o)-> cryptoList.add(cs.convert(o, Crypto.class)));
        return cryptoList;
    }

    @Override
    public boolean updateCryptoPrice(Long id, Double newValue) {

        CryptoPriceEntity cryptoPriceEntity = em.find(CryptoPriceEntity.class, id);
        em.refresh(cryptoPriceEntity, LockModeType.OPTIMISTIC);
        cryptoPriceEntity.setPriceUSD(newValue);

        return true;
    }

    //TODO убить в конце
    @Override
    public Crypto add(Crypto crypto) {
        LocalDateTime ldt = LocalDateTime.now();
        crypto.setDtCreate(ldt);
        crypto.setDtUpdate(ldt);


        CryptoPriceEntity cryptoPriceEntity = new CryptoPriceEntity();
        cryptoPriceEntity.setId(crypto.getId());
        cryptoPriceEntity.setPriceUSD(1d);
        cryptoPriceEntity.setDtCreate(ldt);
        cryptoPriceEntity.setDtUpdate(ldt);
        cryptoPriceStorage.save(cryptoPriceEntity);
        return cs.convert(cryptoStorage.save(cs.convert(crypto, CryptoEntity.class)), Crypto.class);
    }

    @Override
    public boolean checkSymbol(String symbol) {
        cryptoStorage.findBySymbol(symbol).orElseThrow(() -> new EntityNotFound("Передана несуществующая монета"));
        return true;
    }
}
