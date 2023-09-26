package com.nagarro.notesapp.impl;

import com.nagarro.notesapp.models.User;
import com.nagarro.notesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.getUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("No User Found");
		}
		return user;
	}

}
