package com.DawidM.SavingDataCar.repository;

import com.DawidM.SavingDataCar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    //Insert into car where cars_id_user
    //@Query("insert into car (cars_id_user)")
    //String InsertIntoCar
    //@Query("select i from Item i where i.quantity>20")
    //public void setIdUserToCar();
    @Query("select i from Car i where user.idUser=:idUser")
    List<Car> getAuthenticatedUserCars(@Param("idUser")long idUser);

}
