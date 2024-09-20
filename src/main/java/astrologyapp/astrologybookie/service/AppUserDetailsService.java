package com.example.astrologyapp.service;

import astrologyapp.astrologybookie.model.user.CurrentUserDetails;
import astrologyapp.astrologybookie.repository.UserRepository;
import astrologyapp.astrologybookie.util.customException.UserNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<astrologyapp.astrologybookie.model.User> byEmail = userRepository.findByEmail(email);

        if (byEmail.isEmpty()) {
            throw new UserNotFoundException("There is no user with this email or phone number in our system");
        }

        astrologyapp.astrologybookie.model.User user = byEmail.get();
        List<SimpleGrantedAuthority> collect = user.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toList());

        return new CurrentUserDetails(
                user.getEmail(),
                user.getPassword(),
                collect,
                user.getId());
    }
}
