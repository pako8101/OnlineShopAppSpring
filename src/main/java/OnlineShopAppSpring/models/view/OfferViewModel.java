package OnlineShopAppSpring.models.view;

import java.math.BigDecimal;

public class OfferViewModel {

    private String id;
    private  String description;
    private BigDecimal price;

    public OfferViewModel() {
    }

    public String getId() {
        return id;
    }

    public OfferViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
