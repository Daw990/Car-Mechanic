package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.entity.UserData;

public interface SignUpService {

    void signUpUser(User user, UserData userData);
    long getAuthenticatedUserId();
    User findById(Long id);
    User save(User user);
}
