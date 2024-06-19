package OnlineShopAppSpring.repositories;

import OnlineShopAppSpring.models.entities.Offer;
import OnlineShopAppSpring.models.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    List<Offer> findAllByConditionName(ConditionName conditionName);
}
