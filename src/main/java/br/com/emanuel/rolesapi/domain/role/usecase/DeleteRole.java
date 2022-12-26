package br.com.emanuel.rolesapi.domain.role.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleMember;
import br.com.emanuel.rolesapi.domain.role.RoleMemberFactory;
import br.com.emanuel.rolesapi.domain.role.RoleMembershipDTO;
import br.com.emanuel.rolesapi.domain.role.RoleNotFoundException;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;

@Component
public class DeleteRole {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleMemberFactory roleMemberFactory;
	
	public void deleteById(Long id) {
		Role role = roleRepository.findById(id);
		
		if (role == null) {
			throw new RoleNotFoundException(id);
		}
		
		roleRepository.delete(role);
	}

	public void deleteMembership(RoleMembershipDTO roleMembership) {
		
		RoleMember roleMember = roleMemberFactory.toRoleMember(roleMembership);
		
		RoleMember storedRoleMember = roleRepository.findRoleMembership(roleMember.getUser(), roleMember.getTeam(), roleMember.getRole());
		if (storedRoleMember == null) return;
		
		roleRepository.delete(storedRoleMember);
	}

	
	
}
