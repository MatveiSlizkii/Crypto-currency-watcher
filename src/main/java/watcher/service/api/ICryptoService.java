package watcher.service.api;

import watcher.model.dto.Crypto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICryptoService {
    Crypto getCryptoPrice(String symbol);
    Page<Crypto> getAllPage(Pageable pageable);
    List<Crypto> getAllList ();
    boolean updateCryptoPrice(Long id, Double newValue);
    boolean checkSymbol(String symbol);

    Crypto add (Crypto crypto);

}
