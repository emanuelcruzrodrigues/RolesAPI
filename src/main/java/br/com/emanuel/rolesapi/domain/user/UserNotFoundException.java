package br.com.emanuel.rolesapi.domain.user;

import br.com.emanuel.rolesapi.domain.RolesAPIException;

@SuppressWarnings("serial")
public class UserNotFoundException extends RolesAPIException {

	public UserNotFoundException(String id) {
		super(String.format("No user found with the giving id: %s", id));
	}
	
}
