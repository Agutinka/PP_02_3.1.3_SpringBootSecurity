package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @PostConstruct
//    public void init() {
//        userService.createAdmin("admin", "admin");
//    }

//    @PostConstruct
//    public void initUsers() {
//        Role adminRole = new Role("ADMIN");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRole);
//
//        roleService.save(adminRole);
//
//        Role userRole = new Role("USER");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//
//        roleService.save(userRole);
//
//        User admin = new User("admin", "admin", 25, adminRoles);
//        userService.save(admin);
//
//        User user = new User("user", "user", 19, userRoles);
//        userService.save(user);
//    }
}
