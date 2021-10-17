package com.example.modulocorreo.services.implementation;

import com.example.modulocorreo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.modulocorreo.entities.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username Not found" + username);
        }
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}