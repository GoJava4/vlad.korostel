package com.morkva.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;

@Service("assembler")
public class Assembler {
    User buildUserFromUserEntity(com.morkva.entities.User userEntity) {
        String username = userEntity.getLogin();
        String password = userEntity.getPassword();
        boolean enabled = userEntity.isActive();
        boolean accountNonExpired = userEntity.isActive();
        boolean credentialsNonExpired = userEntity.isActive();
        boolean accountNonLocked = userEntity.isActive();
        Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole().getName()));
        User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return user;
    }
}
