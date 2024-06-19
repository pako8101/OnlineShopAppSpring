package OnlineShopAppSpring.models.dtos;

import OnlineShopAppSpring.models.entities.Condition;
import OnlineShopAppSpring.models.enums.ConditionName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class OfferAddDto {
    @Size(min = 2, max = 50, message = "Description must be between 2 and 50 symbols!")
    private String description;
    @DecimalMin(value = "0",message = "The price must be a positive number")
    private BigDecimal price;

    @NotNull(message = "Invalid condition")
    private ConditionName condition;

    public OfferAddDto() {
    }

    public String getDescription() {
        return description;
    }

    public OfferAddDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferAddDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public OfferAddDto setCondition(ConditionName condition) {
        this.condition = condition;
        return this;
    }
}
