package com.ecommerce.ecommerce.implementation;

import com.ecommerce.ecommerce.repositories.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleService {
	
	@Autowired
	@Qualifier("userRoleRepository")
	public IUserRoleRepository userRoleRepository;
	
	public void saveUser(com.ecommerce.ecommerce.entities.UserRole user) {
		userRoleRepository.save(user);
		
		
	}
}