package OnlineShopAppSpring.models.dtos;

import OnlineShopAppSpring.models.entities.Offer;

import java.math.BigDecimal;

public class BoughtOffersDto {
    private String description;

    private BigDecimal price;

    public BoughtOffersDto() {
    }
    public BoughtOffersDto(Offer offer) {
        description = offer.getDescription();
        price = offer.getPrice();
    }

    public String getDescription() {
        return description;
    }

    public BoughtOffersDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BoughtOffersDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
