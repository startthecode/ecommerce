package org.authetication.ecommerce.services.imp;

import org.authetication.ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImp implements UserDetailsService{

    private final UserRepository userRepository;

     public UserDetailsImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(UserPrincipleImp::new).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    

}
