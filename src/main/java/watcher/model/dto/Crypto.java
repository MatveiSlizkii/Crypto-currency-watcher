package watcher.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Crypto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("price_usd")
    private Double priceUSD;
    @JsonIgnore
    private LocalDateTime dtCreate;
    @JsonIgnore
    private LocalDateTime dtUpdate;

    public Crypto() {
    }

    public Crypto(Long id, String symbol,
                  Double priceUSD, LocalDateTime dtCreate,
                  LocalDateTime dtUpdate) {
        this.id = id;
        this.symbol = symbol;
        this.priceUSD = priceUSD;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", price=" + priceUSD +
                '}';
    }
}
