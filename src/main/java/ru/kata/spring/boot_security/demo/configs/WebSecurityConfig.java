package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.kata.spring.boot_security.demo.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }

//    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Отключение CSRF защиты
                .authorizeRequests()  // Настройка правил авторизации
                .antMatchers("/", "/index", "login").permitAll()  // Разрешение доступа без аутентификации
                .antMatchers("/admin/**").hasAuthority("ADMIN")  // для доступа к путям, начинающимся с /admin/, должна быть роль ADMIN
                .antMatchers("/user/**").hasAnyAuthority("ADMIN","USER")  // для доступа к путям, начинающимся с /user/, должна быть роль ADMIN или USER
                .and()  // Завершение настройки для authorizeRequests
                .formLogin()  // Включение формы для аутентификации
                .loginPage("/login")  // Указание страницы с формой входа
                .successHandler(successUserHandler).permitAll()  // Установка пользовательского обработчика успешной аутентификации для всех пользователей
                .and()  // Завершение настройки для formLogin
                .logout()  // Включение поддержки выхода из системы
                .logoutUrl("/logout").permitAll()  // Указание URL для выполнения выхода с доступом для всех пользователей
                .logoutSuccessUrl("/login");  // Установка URL для переадресации после успешного выхода
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return userService;
    }

}