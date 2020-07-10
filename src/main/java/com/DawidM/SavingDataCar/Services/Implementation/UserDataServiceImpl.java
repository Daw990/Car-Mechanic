package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.Services.UserDataService;
import com.DawidM.SavingDataCar.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;

    @Autowired
    UserDataServiceImpl(UserDataRepository userDataRepository){
        this.userDataRepository = userDataRepository;
    }
}
