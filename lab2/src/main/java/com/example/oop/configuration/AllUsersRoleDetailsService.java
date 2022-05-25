package com.example.oop.configuration;

import com.example.oop.repositories.DispatcherRepository;
import com.example.oop.repositories.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllUsersRoleDetailsService implements UserDetailsService {
    private final DispatcherRepository dispatcherRepository;
    private final TenantRepository tenantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<? extends UserDetails> tenant = tenantRepository.findByUsername(username);
        if (tenant.isPresent()) {
            return tenant.get();
        }
        Optional<? extends UserDetails> dispatcher = dispatcherRepository.findByUsername(username);
        if (dispatcher.isPresent()) {
            return dispatcher.get();
        }
        throw new RuntimeException();
    }
}
