package watcher.dao.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "crypto_price", schema = "app")
public class CryptoPriceEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column (name = "price_usd")
    private Double priceUSD;
    @Column(name = "dt_create", nullable = false)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dtUpdate;

    public CryptoPriceEntity() {
    }

    public CryptoPriceEntity(Long id, Double priceUSD,
                             LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.id = id;
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
        return "CryptoValueEntity{" +
                "id=" + id +
                ", valueUSD=" + priceUSD +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                '}';
    }
}
