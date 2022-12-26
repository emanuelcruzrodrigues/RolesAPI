package br.com.emanuel.rolesapi.domain.role;

import br.com.emanuel.rolesapi.domain.RolesAPIException;

@SuppressWarnings("serial")
public class MembershipNotFoundException extends RolesAPIException {

	public MembershipNotFoundException(String userId, String teamId) {
		super(String.format("No role found to user id: %s and team id: %s", userId, teamId));
	}

}
