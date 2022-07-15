package watcher.dao.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "crypto", schema = "app")
public class CryptoEntity {
    @Id
    @Column (name = "id", nullable = false)
    private Long id;
    @Column (name = "symbol", nullable = false)
    private String symbol;
    @Column(name = "dt_create", nullable = false)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dtUpdate;

    public CryptoEntity() {
    }

    public CryptoEntity(Long id, String symbol,
                        LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.id = id;
        this.symbol = symbol;
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
        return "CryptoEntity{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                '}';
    }
}
