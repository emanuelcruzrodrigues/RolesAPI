package br.com.emanuel.rolesapi.domain.team;

import br.com.emanuel.rolesapi.domain.RolesAPIException;

@SuppressWarnings("serial")
public class TeamNotFoundException extends RolesAPIException {

	public TeamNotFoundException(String id) {
		super(String.format("No team found with the giving id: %s", id));
	}
	
}
