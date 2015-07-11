package com.morkva.services;

import com.morkva.entities.User;
import com.morkva.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by koros on 11.07.2015.
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Assembler assembler;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        System.out.println("USER DAO = " + userDao);
        User user = userDao.getByLogin(s);
        System.out.println("USER = " + user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return assembler.buildUserFromUserEntity(user);
    }
}
