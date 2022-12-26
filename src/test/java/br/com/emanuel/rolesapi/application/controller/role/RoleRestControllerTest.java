package br.com.emanuel.rolesapi.application.controller.role;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;
import jakarta.transaction.Transactional;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RoleRestControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private RoleRepository roleRepository;

	private Role newRole;
	
	@Test
	@Order(1)
	void test_Create_A_New_Role() {
		
		newRole = testRestTemplate.postForObject("/roles", new Role("New Role"), Role.class);
		
		Role storedRole = roleRepository.findById(newRole.getId());
		assertEquals(storedRole.getName(), newRole.getName());
	}
	
	@Test
	@Order(2)
	void test_Does_Not_Create_A_Role_If_It_Already_Exists() {
		
		Role anotherNewRole = testRestTemplate.postForObject("/roles", new Role(newRole.getName()), Role.class);
		
		assertEquals(newRole.getId(), anotherNewRole.getId());
	}
	
	@Test
	@Order(3)
	void test_List_Roles() {
		List<?> list = testRestTemplate.getForObject("/roles", List.class);
		assertTrue(list.size() > 0);
	}
	
	@Test
	@Order(4)
	void get_A_Role() {
		Role role = testRestTemplate.getForObject(String.format("/roles/%d", newRole.getId()), Role.class);
		assertEquals(newRole.getName(), role.getName());
	}
	
	@Test
	@Transactional
	@Order(5)
	void test_Update_A_Role() {
		
		Role role = new Role("New role name");
		
		testRestTemplate.put(String.format("/roles/%d", newRole.getId()), role);
		
		Role stored = roleRepository.findById(newRole.getId());
		assertEquals("New role name", stored.getName());
		
	}
	
	@Test
	@Order(6)
	void test_Delete_A_Role() {
		testRestTemplate.delete(String.format("/roles/%d", newRole.getId()));
		assertNull(roleRepository.findById(newRole.getId()));
	}

}
