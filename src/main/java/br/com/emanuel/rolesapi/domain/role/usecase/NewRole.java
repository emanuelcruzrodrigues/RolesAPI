package br.com.emanuel.rolesapi.domain.role.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;
import jakarta.transaction.Transactional;

@Component
public class NewRole {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public Role createRole(String roleName) {
		
		//check if the role already exists
		Role role = roleRepository.findByName(roleName);
		if (role != null) {
			return role;
		}
		
		//if does not exist a role with the same name, create the role
		role = new Role(roleName);
		roleRepository.save(role);
		
		return role;
	}

}
