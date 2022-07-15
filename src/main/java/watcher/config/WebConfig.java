package watcher.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import watcher.dao.api.ICryptoPriceStorage;
import watcher.dao.converters.CryptoEntityToCryptoConverter;
import watcher.dao.converters.CryptoToCryptoEntityConverter;
import watcher.dao.converters.UserEntityToUserConverter;
import watcher.dao.converters.UserToUserEntityConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ICryptoPriceStorage cryptoPriceStorage;

    public WebConfig(ICryptoPriceStorage cryptoPriceStorage) {
        this.cryptoPriceStorage = cryptoPriceStorage;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CryptoEntityToCryptoConverter(cryptoPriceStorage));
        registry.addConverter(new CryptoToCryptoEntityConverter());
        registry.addConverter(new UserEntityToUserConverter());
        registry.addConverter(new UserToUserEntityConverter());
    }
}
