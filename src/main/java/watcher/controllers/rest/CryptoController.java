package watcher.controllers.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import watcher.model.dto.Crypto;
import watcher.model.dto.Notification;
import watcher.service.api.ICryptoService;
import watcher.service.api.INotificationService;
import watcher.service.exception.EntityNotFound;
import java.util.List;


@RestController
@RequestMapping(value = "/crypto", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CryptoController {

    private final ICryptoService cryptoService;
    private final INotificationService notificationService;

    public CryptoController(ICryptoService cryptoService, INotificationService notificationService) {
        this.cryptoService = cryptoService;
        this.notificationService = notificationService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Crypto> getAll() {
        return this.cryptoService.getAllList();
    }

    @GetMapping(value = {"/page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<Crypto> index(@RequestParam  int page,
                               @RequestParam  int size) {
        if (page < 0 ){
            throw new EntityNotFound("Вы ввели недопустимое значение page");
        }
        if (size < 1 ){
            throw new EntityNotFound("Вы ввели недопустимое значение size");
        }
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        return cryptoService.getAllPage(pageable);
    }

    @GetMapping(value = "/{symbol}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Crypto get(@PathVariable String symbol) {
        return this.cryptoService.getCryptoPrice(symbol);
    }

    @PostMapping(value = "/notify")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification notifyPrice(@RequestBody Notification notification) {
        return this.notificationService.add(notification);
    }
    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Crypto addCrypto(@RequestBody Crypto crypto) {
        return this.cryptoService.add(crypto);
    }
}
