package OnlineShopAppSpring.services;

import OnlineShopAppSpring.models.entities.Condition;
import OnlineShopAppSpring.models.enums.ConditionName;
import OnlineShopAppSpring.models.view.OfferViewModel;

import java.util.List;

public interface ConditionService {
    void initConditions();
    Condition getConditionByName(ConditionName conditionName);
}
