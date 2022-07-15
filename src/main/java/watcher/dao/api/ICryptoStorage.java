package watcher.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watcher.dao.entity.CryptoEntity;

import java.util.Optional;

@Repository
public interface ICryptoStorage extends JpaRepository<CryptoEntity, Long> {
    Optional<CryptoEntity> findBySymbol(String symbol);

}
