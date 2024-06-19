package OnlineShopAppSpring.models.dtos;

import OnlineShopAppSpring.models.entities.Offer;
import OnlineShopAppSpring.models.enums.ConditionName;

import java.util.UUID;

public class MyOfferDto extends BoughtOffersDto{

    private UUID id;
    private ConditionName condition;

    public MyOfferDto() {
    }
    public MyOfferDto(Offer offer) {
    super();
    id = offer.getId();
    condition = offer.getCondition().getName();
    }
    public UUID getId() {
        return id;
    }

    public MyOfferDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public MyOfferDto setCondition(ConditionName condition) {
        this.condition = condition;
        return this;
    }
}
