package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String USER_WITH_USERNAME_U_NOT_EXIST = "User with username %u not exist";

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException(String.format(USER_WITH_USERNAME_U_NOT_EXIST, username));
        }
        return optionalUser.get();
    }
}
