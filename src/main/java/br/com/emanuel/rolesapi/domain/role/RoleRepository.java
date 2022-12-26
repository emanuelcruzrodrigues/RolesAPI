package br.com.emanuel.rolesapi.domain.role;

import java.util.List;

import br.com.emanuel.rolesapi.domain.team.Team;
import br.com.emanuel.rolesapi.domain.user.User;

public interface RoleRepository {

	Role findByName(String roleName);

	void save(Role role);

	Role findById(Long id);

	List<Role> listAll();

	void delete(Role role);

	RoleMember findRoleMembership(User user, Team team, Role role);

	void save(RoleMember roleMember);

	List<RoleMembershipDTO> findMembership(String roleName);

	void delete(RoleMember storedRoleMember);

}
