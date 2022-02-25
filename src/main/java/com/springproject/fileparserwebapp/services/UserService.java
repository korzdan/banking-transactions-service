package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist..."));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<Transaction> getTransactionsOfUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User hasn't been found.")
        );
        return new ArrayList<>(user.getTransactions());
    }
}
