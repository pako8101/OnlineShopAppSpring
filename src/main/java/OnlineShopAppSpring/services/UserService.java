package OnlineShopAppSpring.services;

import OnlineShopAppSpring.models.dtos.UserLoginBindingModel;
import OnlineShopAppSpring.models.dtos.UserRegisterBindingModel;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);
}
