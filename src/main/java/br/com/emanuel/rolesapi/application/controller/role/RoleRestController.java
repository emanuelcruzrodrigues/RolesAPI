package br.com.emanuel.rolesapi.application.controller.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleFacade;

@RestController
public class RoleRestController {
	
	@Autowired
	private RoleFacade roleFacade;
	
	@GetMapping("/roles")
	List<Role> listAll() {
		return roleFacade.listAll();
	}
	
	@GetMapping("/roles/{id}")
	Role get(@PathVariable Long id) {
	    return roleFacade.findById(id);
	}

	@PostMapping("/roles")
	Role newRole(@RequestBody Role role) {
		return roleFacade.createRole(role);
	}
	
	@PutMapping("/roles/{id}")
	Role update(@RequestBody Role role, @PathVariable Long id) {
		return roleFacade.update(role, id);
	}
	
	@PatchMapping("/roles/{id}")
	Role patch(@RequestBody Role role, @PathVariable Long id) {
		return roleFacade.patch(role, id);
	}
	
	@DeleteMapping("/roles/{id}")
	void delete(@PathVariable Long id) {
		roleFacade.deleteById(id);
	}
	
}
