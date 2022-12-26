package br.com.emanuel.rolesapi.application.controller.role;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleMember;
import br.com.emanuel.rolesapi.domain.role.RoleMembershipDTO;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;
import br.com.emanuel.rolesapi.domain.team.Team;
import br.com.emanuel.rolesapi.domain.team.TeamRepository;
import br.com.emanuel.rolesapi.domain.user.User;
import br.com.emanuel.rolesapi.domain.user.UserRepository;
import jakarta.transaction.Transactional;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RoleMembershipRestControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	private User user;
	private Team team;
	private Role roleDeveloper;
	
	@Transactional
	@Test
	@Order(0)
	void setUp() {
		roleDeveloper = roleRepository.findByName("developer");
		user = userRepository.findById("fa1529de-5f20-49a7-ad25-a494008dd322");
		team = teamRepository.findById("7676a4bf-adfe-415c-941b-1739af07039b");
	}
	
	@Transactional
	@Test
	@Order(1)
	void test_Assign_A_New_Role_To_A_Team_Member() {
		
		testRestTemplate.postForEntity("/membership", new RoleMembershipDTO("Developer", user.getId(), team.getId()), Object.class);
		
		RoleMember storedMembership = roleRepository.findRoleMembership(user, team, null);
		assertEquals(roleDeveloper.getId(), storedMembership.getRole().getId());
	}

}
