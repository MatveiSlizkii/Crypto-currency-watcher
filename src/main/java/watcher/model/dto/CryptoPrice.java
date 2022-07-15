package watcher.model.dto;

public class CryptoPrice {

    private Long id;
    private Double priceUSD;

    public CryptoPrice() {
    }

    public CryptoPrice(Long id, Double priceUSD) {
        this.id = id;
        this.priceUSD = priceUSD;
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

    @Override
    public String toString() {
        return "CryptoValue{" +
                "id=" + id +
                ", value=" + priceUSD +
                '}';
    }
}
