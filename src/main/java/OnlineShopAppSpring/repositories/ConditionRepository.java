package OnlineShopAppSpring.repositories;

import OnlineShopAppSpring.models.entities.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ConditionRepository extends JpaRepository<Condition, UUID> {
}
