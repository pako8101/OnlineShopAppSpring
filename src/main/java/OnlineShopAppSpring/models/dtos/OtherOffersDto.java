package OnlineShopAppSpring.models.dtos;

import OnlineShopAppSpring.models.entities.Offer;

public class OtherOffersDto extends MyOfferDto{

    private String sellerUsername;

    public OtherOffersDto() {
    }
    public OtherOffersDto(Offer offer) {
        super(offer);
        sellerUsername = offer.getCreatedBy().getUsername();
    }
    public String getSellerUsername() {
        return sellerUsername;
    }

    public OtherOffersDto setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }
}
