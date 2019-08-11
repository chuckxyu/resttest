package com.example.articulos.service;

import java.util.HashSet;
import java.util.Set;

import com.example.articulos.model.Role;
import com.example.articulos.model.User;
import com.example.articulos.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
@Autowired
private UserRepository userRepository;

@Override
@Transactional(readOnly = true)
public UserDetails loadUserByUsername(String username) 
throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : user.getRoles()){
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }

    return new org.springframework.security.core.userdetails.User(user.getUsername(), 
    user.getPassword(), grantedAuthorities);
}
}