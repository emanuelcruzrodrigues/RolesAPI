package br.com.emanuel.rolesapi.domain.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emanuel.rolesapi.domain.role.usecase.DeleteRole;
import br.com.emanuel.rolesapi.domain.role.usecase.NewRole;
import br.com.emanuel.rolesapi.domain.role.usecase.NewRoleMembership;
import br.com.emanuel.rolesapi.domain.role.usecase.SearchRole;
import br.com.emanuel.rolesapi.domain.role.usecase.UpdateRole;
import jakarta.transaction.Transactional;

@Service
public class RoleFacade {
	
	@Autowired
	private NewRole newRole;
	
	@Autowired
	private SearchRole searchRole;
	
	@Autowired
	private UpdateRole updateRole;
	
	@Autowired
	private DeleteRole deleteRole;
	
	@Autowired
	private NewRoleMembership newRoleMembership;
	
	@Transactional
	public Role createRole(Role role) {
		Role storedRole = newRole.createRole(role.getName());
		return storedRole;
	}

	public List<Role> listAll() {
		return searchRole.listAll();
	}

	public Role findById(Long id) {
		return searchRole.findById(id);
	}

	@Transactional
	public Role update(Role role, Long id) {
		return updateRole.updateRole(id, role, false);
	}

	@Transactional
	public Role patch(Role role, Long id) {
		return updateRole.updateRole(id, role, true);
	}

	@Transactional
	public void deleteById(Long id) {
		deleteRole.deleteById(id);
	}

	@Transactional
	public void createMembership(RoleMembershipDTO roleMembership) {
		newRoleMembership.createMembership(roleMembership);
	}

	@Transactional
	public Role findRole(RoleMembershipDTO roleMembership) {
		return searchRole.findRole(roleMembership);
	}

	@Transactional
	public List<RoleMembershipDTO> findMembership(String roleName) {
		return searchRole.findMembership(roleName);
	}

	@Transactional
	public void deleteMembership(RoleMembershipDTO roleMembership) {
		deleteRole.deleteMembership(roleMembership);
	}

}
