package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ユーザー情報取得
        final MUser loginUser = service.getLoginUser(username);
        if (loginUser == null) throw new UsernameNotFoundException("user not found");

        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(authority);

        return new User(loginUser.getUserId(), loginUser.getPassword(), authorities);
    }
}
