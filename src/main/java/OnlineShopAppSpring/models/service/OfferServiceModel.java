package OnlineShopAppSpring.models.service;

import OnlineShopAppSpring.models.entities.Condition;
import OnlineShopAppSpring.models.enums.ConditionName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class OfferServiceModel {
    @NotBlank(message = "Cannot be blank")
    @Size(min = 2, max = 50, message = "Description must be between 2 and 50 symbols!")
    private String description;
    @DecimalMin(value = "0",message = "The price must be a positive number")
    private BigDecimal price;

    @NotNull(message = "Invalid condition")
    private ConditionName condition;

    public OfferServiceModel() {
    }

    public String getDescription() {
        return description;
    }

    public OfferServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public OfferServiceModel setCondition(ConditionName condition) {
        this.condition = condition;
        return this;
    }
}
