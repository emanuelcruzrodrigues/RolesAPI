package br.com.emanuel.rolesapi.domain.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.rolesapi.domain.team.Team;
import br.com.emanuel.rolesapi.domain.team.TeamNotFoundException;
import br.com.emanuel.rolesapi.domain.team.TeamRepository;
import br.com.emanuel.rolesapi.domain.user.User;
import br.com.emanuel.rolesapi.domain.user.UserNotFoundException;
import br.com.emanuel.rolesapi.domain.user.UserRepository;

@Component
public class RoleMemberFactory {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public RoleMember toRoleMember(RoleMembershipDTO roleMembership) {
		
		User user = userRepository.findById(roleMembership.getUserId());
		if (user == null) {
			throw new UserNotFoundException(roleMembership.getUserId());
		}
		
		Team team = teamRepository.findById(roleMembership.getTeamId());
		if (team == null) {
			throw new TeamNotFoundException(roleMembership.getUserId());
		}
		
		Role role = null;
		if (roleMembership.getRoleName() != null) {
			role = roleRepository.findByName(roleMembership.getRoleName());
			if (role == null) {
				throw new RoleNotFoundException(roleMembership.getRoleName());
			}
		}
		
		RoleMember roleMember = new RoleMember(role, user, team);
		return roleMember;
	}
	
}
