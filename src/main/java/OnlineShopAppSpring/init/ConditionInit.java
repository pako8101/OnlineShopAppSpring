package OnlineShopAppSpring.init;

import OnlineShopAppSpring.models.entities.Condition;
import OnlineShopAppSpring.models.enums.ConditionName;
import OnlineShopAppSpring.repositories.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ConditionInit implements CommandLineRunner {
    private final ConditionRepository conditionRepository;

    public ConditionInit(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       boolean hasCondition = conditionRepository.count()>0;
       if (!hasCondition){
           List<Condition>conditions = new ArrayList<>();

           Arrays.stream(ConditionName.values())
                   .forEach(conditionName -> {
                       Condition condition = new Condition();
                       condition.setName(conditionName);
                   });
                   conditionRepository.saveAll(conditions);
       }
    }
}
