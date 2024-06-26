package OnlineShopAppSpring.services.impl;

import OnlineShopAppSpring.models.dtos.UserLoginBindingModel;
import OnlineShopAppSpring.models.dtos.UserRegisterBindingModel;
import OnlineShopAppSpring.models.entities.User;
import OnlineShopAppSpring.repositories.UserRepository;
import OnlineShopAppSpring.services.LoggedUser;
import OnlineShopAppSpring.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final LoggedUser loggedUser;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {

        if (userRegisterBindingModel==null){
            return false;
        }
        String username = userRegisterBindingModel.getUsername();
        if (this.userRepository.findByUsername(username) != null) {
            return false;
        }

        User user = new User();

        user.setUsername(userRegisterBindingModel.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        user.setEmail(userRegisterBindingModel.getEmail());
this.userRepository.saveAndFlush(user);
return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
User user = findUserByUsername(userLoginBindingModel.getUsername());
if (user!= null && passwordEncoder.matches(userLoginBindingModel.getPassword(),user.getPassword())){
    loggedUser.setUsername(user.getUsername());
    loggedUser.setLogged(true);
    return true;
}

        return false;
    }

    private User findUserByUsername (String username){
        return userRepository.findByUsername(username);
    }
}
