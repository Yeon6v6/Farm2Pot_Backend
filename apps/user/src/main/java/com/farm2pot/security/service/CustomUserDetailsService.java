package com.farm2pot.security.service;


import com.farm2pot.user.entity.User;
import com.farm2pot.user.repository.UserRepository;
import com.farm2pot.security.dto.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.farm2pot.auth.service
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */


@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        try {
            User user = userRepository.findById(Long.parseLong(id))
                    .orElseThrow(() -> new UsernameNotFoundException(id));

            log.info("✅ User authenticated successfully! ID : {} name : {}", id, user.getName());

            return new CustomUserDetails(user);

        } catch (UsernameNotFoundException e) {
            log.warn("⚠️ User not found during authentication: {}", id);
            throw new UsernameNotFoundException(e.getMessage(), e);

        } catch (Exception e) {
            log.error("❌ Unexpected error during user load: {}", e.getMessage(), e);
            throw new UsernameNotFoundException("Unexpected authentication error", e);
        }
    }
}
