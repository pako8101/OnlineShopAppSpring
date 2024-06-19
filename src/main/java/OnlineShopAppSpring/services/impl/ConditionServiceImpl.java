package OnlineShopAppSpring.services.impl;

import OnlineShopAppSpring.models.entities.Condition;
import OnlineShopAppSpring.models.enums.ConditionName;
import OnlineShopAppSpring.models.view.OfferViewModel;
import OnlineShopAppSpring.repositories.ConditionRepository;
import OnlineShopAppSpring.services.ConditionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService {
private final ConditionRepository conditionRepository;

    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void initConditions() {

    }

    @Override
    public Condition getConditionByName(ConditionName conditionName) {
        return conditionRepository.findByName(conditionName);
    }
}
