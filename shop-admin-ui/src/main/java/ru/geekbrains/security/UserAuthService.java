package ru.geekbrains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.repo.UserRepository;

import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
            .map(user -> new User(
                user.getName(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))))
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
