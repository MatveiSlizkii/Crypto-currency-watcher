package watcher.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private UUID id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("symbol")
    private String symbol;
    private double startPrice;
    private boolean enable;
    @JsonIgnore
    private LocalDateTime dtCreate;
    @JsonIgnore
    private LocalDateTime dtUpdate;

    public Notification() {
    }

    public Notification(UUID id, String username, String symbol, double startPrice,
                        boolean enable, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.id = id;
        this.username = username;
        this.symbol = symbol;
        this.startPrice = startPrice;
        this.enable = enable;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", symbol='" + symbol + '\'' +
                ", startPrice=" + startPrice +
                ", enable=" + enable +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                '}';
    }
    public static class Builder {
        private UUID id;
        private String username;
        private String symbol;
        private Double startPrice;
        private boolean enable;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;

        private Builder() {
        }

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder setStartPrice(Double startPrice) {
            this.startPrice = startPrice;
            return this;
        }

        public Builder setEnable(boolean enable) {
            this.enable = enable;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Notification build() {
            return new Notification(id, username, symbol, startPrice, enable, dtCreate, dtUpdate);
        }
}}
