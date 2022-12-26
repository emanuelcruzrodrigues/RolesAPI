package br.com.emanuel.rolesapi.domain.role.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.rolesapi.domain.role.RoleMember;
import br.com.emanuel.rolesapi.domain.role.RoleMemberFactory;
import br.com.emanuel.rolesapi.domain.role.RoleMembershipDTO;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;

@Component
public class NewRoleMembership {

	@Autowired
	private RoleMemberFactory roleMemberFactory;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void createMembership(RoleMembershipDTO roleMembership) {

		RoleMember newMembership = roleMemberFactory.toRoleMember(roleMembership);

		RoleMember storedMembership = roleRepository.findRoleMembership(newMembership.getUser(), newMembership.getTeam(), null);
		if (storedMembership == null) {
			roleRepository.save(newMembership);
		} else {
			storedMembership.setRole(newMembership.getRole());
		}
	}

	

}
