package br.com.emanuel.rolesapi.domain.role.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleNotFoundException;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;

@Component
public class UpdateRole {
	
	@Autowired
	private RoleRepository roleRepository;

	public Role updateRole(Long id, Role newData, boolean onlyNotNullFields) {
		Role role = roleRepository.findById(id);
		
		if (role == null) {
			throw new RoleNotFoundException(id);
		}
		
		if (onlyNotNullFields) {
			role.updateNotNull(newData);
		} else {
			role.update(newData);
		}
		
		return role;
		
	}

	
	
}
