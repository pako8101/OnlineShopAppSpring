package OnlineShopAppSpring.services;

import OnlineShopAppSpring.models.dtos.OfferHomeDto;
import OnlineShopAppSpring.models.enums.ConditionName;
import OnlineShopAppSpring.models.service.OfferServiceModel;
import OnlineShopAppSpring.models.view.OfferViewModel;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    void addOffer(OfferServiceModel offerServiceModel);

    OfferHomeDto getOffersFotHomePage();

   List<OfferViewModel>getOffersByCondition(ConditionName conditionName);

    void removeAllOffers();

    void buy(UUID id);
}
