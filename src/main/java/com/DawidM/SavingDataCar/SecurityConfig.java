package com.DawidM.SavingDataCar;

import com.DawidM.SavingDataCar.Services.Implementation.UserDetailsServiceImpl;
import com.DawidM.SavingDataCar.component.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug=false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired//nie mozna wstrzyknac przez konstruktor bo jest juz w CustomAuthenticationProvider
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception{
        authentication.userDetailsService(userDetailsServiceImpl);
        authentication.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //kazde zadanie moze byc wykonane przez zalogowanego uzytkownika domyslnie ponizej jawnie
        http
                .authorizeRequests()
                .antMatchers("/login", "/","/sign_up","/logout","/test/**").permitAll() //static tez trzeb dodac zeby style sie ladowaly
                .antMatchers("/css/**","/js/**","/images/**").permitAll()
                .antMatchers("/user/newUser","/user/save","/user/makeVisit").permitAll()
                .antMatchers("/user/repairsList","/confirm_email").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()//my login page
                .loginPage("/login")
                .defaultSuccessUrl("/user/userPanel", true)
                .and()
            .logout()
                .logoutSuccessUrl("/?logout")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean //its for test without hashing password
//    public static NoOpPasswordEncoder passwordEncoder(){
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
}
