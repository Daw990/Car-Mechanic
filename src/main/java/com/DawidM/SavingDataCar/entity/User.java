package com.DawidM.SavingDataCar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUser;
    @Column
    private String email;
    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private UserData userData;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   // feth eager load all cars
    @JoinColumn
    private List<Car> cars;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}  )
    @JoinColumn
    private List<Visit> visits;
    //CascadeType Refresh - add refresh to all linked entites when uptade
    //CascadeType Delete - delete all linked entities
    //CascadeType Persist - save linked entities
    //CascadeType Detach -
    //CascadeType Merge -
    //CascadeType All - all in one

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;

    public User(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    // User Builder
    public static User of(String email, String password){

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.roles = new ArrayList<Role>();

        return user;
    }

    public void addCar(Car car){
        if(cars == null){
            cars = new ArrayList<>();
        }
        cars.add(car);
    }


    public void addVisit(Visit visit){
        if(visits == null){
            visits = new ArrayList<>();
        }
        visits.add(visit);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getRole())).collect(Collectors.toList());
         //learn streams. map: operacja na kazdym elemencie strumienia
                                            // r-kazdy obiektr przekazywany do konstruktora.
                                             //przekonwertowanie stringa z rolami do obiektu implementujacego interfejs:
                                            // SimpleGrantedAuthority i trzeba zworiocic kolekce
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
