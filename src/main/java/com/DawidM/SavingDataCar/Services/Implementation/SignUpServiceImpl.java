package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.Services.SignUpService;
import com.DawidM.SavingDataCar.component.mailBuilder.SignUpMail;
import com.DawidM.SavingDataCar.component.mailBuilder.StringFactory;
import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.Role;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.entity.UserData;
import com.DawidM.SavingDataCar.repository.RoleRepository;
import com.DawidM.SavingDataCar.repository.UserDataRepository;
import com.DawidM.SavingDataCar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

@Component
public class SignUpServiceImpl implements SignUpService, SecurityContext {

    private static final int TOKEN_LENGTH = 20;

    private UserRepository userRepository;
    private UserDataRepository userDataRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private SignUpMail signUpMail;
    
    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                             UserDataRepository userDataRepository, RoleRepository roleRepository,
                             SignUpMail signUpMail){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDataRepository = userDataRepository;
        this.roleRepository = roleRepository;
        this.signUpMail = signUpMail;
    }

    @Override
    public void signUpUser(User user, UserData userData) {

        Assert.isNull(user.getIdUser(), "Can't sign up given user, it already has set id. User: " + user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> findRole = roleRepository.findByRole("USER");
        if(findRole.isPresent()){//jezeli udasie pobrac role to bedziemy ja przypisaywac
            user.getRoles().add(findRole.get());
        }
        String token = StringFactory.getRandomString(TOKEN_LENGTH);
        user.setConfirmationToken(token);
        signUpMail.sendConfirmationLink(user.getEmail(), token);

        user.setUserData(userData);
        userRepository.save(user);
        userDataRepository.save(userData);
    }

    public long getAuthenticatedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =(User)authentication.getPrincipal();
        return user.getIdUser();
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);

        User user = null;

        if (result.isPresent()) {
            user = result.get();
        }
        else {
            // we didn't find the user
            throw new RuntimeException("Did not find user id - " + id);
        }
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Authentication getAuthentication() {
        return null;
    }

    @Override
    public void setAuthentication(Authentication authentication) {

    }
}
