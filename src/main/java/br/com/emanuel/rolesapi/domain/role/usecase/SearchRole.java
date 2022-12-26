package br.com.emanuel.rolesapi.domain.role.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.rolesapi.domain.role.MembershipNotFoundException;
import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleMember;
import br.com.emanuel.rolesapi.domain.role.RoleMembershipDTO;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;
import br.com.emanuel.rolesapi.domain.team.Team;
import br.com.emanuel.rolesapi.domain.team.TeamNotFoundException;
import br.com.emanuel.rolesapi.domain.team.TeamRepository;
import br.com.emanuel.rolesapi.domain.user.User;
import br.com.emanuel.rolesapi.domain.user.UserNotFoundException;
import br.com.emanuel.rolesapi.domain.user.UserRepository;

@Component
public class SearchRole {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	public List<Role> listAll() {
		return roleRepository.listAll();
	}

	public Role findById(Long id) {
		return roleRepository.findById(id);
	}

	public Role findRole(RoleMembershipDTO roleMembership) {

		User user = userRepository.findById(roleMembership.getUserId());
		if (user == null) {
			throw new UserNotFoundException(roleMembership.getUserId());
		}
		
		Team team = teamRepository.findById(roleMembership.getTeamId());
		if (team == null) {
			throw new TeamNotFoundException(roleMembership.getUserId());
		}

		RoleMember membership = roleRepository.findRoleMembership(user, team, null);
		if (membership == null) {
			throw new MembershipNotFoundException(roleMembership.getUserId(), roleMembership.getTeamId());
		}
		
		return membership.getRole();
	}

	public List<RoleMembershipDTO> findMembership(String roleName) {
		return roleRepository.findMembership(roleName);
	}

}
