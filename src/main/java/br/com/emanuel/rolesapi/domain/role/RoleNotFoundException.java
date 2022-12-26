package br.com.emanuel.rolesapi.domain.role;

import br.com.emanuel.rolesapi.domain.RolesAPIException;

@SuppressWarnings("serial")
public class RoleNotFoundException extends RolesAPIException {

	public RoleNotFoundException(Long id) {
		super(String.format("No role found with the giving id: %d", id));
	}
	
	public RoleNotFoundException(String name) {
		super(String.format("No role found with the giving name: %s", name));
	}
	
}
