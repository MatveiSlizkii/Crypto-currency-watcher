package watcher.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watcher.dao.entity.CryptoPriceEntity;

@Repository
public interface ICryptoPriceStorage extends JpaRepository<CryptoPriceEntity, Long> {
}
