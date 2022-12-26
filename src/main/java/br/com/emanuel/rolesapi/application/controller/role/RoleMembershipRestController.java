package br.com.emanuel.rolesapi.application.controller.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuel.rolesapi.domain.RolesAPIException;
import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleFacade;
import br.com.emanuel.rolesapi.domain.role.RoleMembershipDTO;

@RestController
public class RoleMembershipRestController {
	
	@Autowired
	private RoleFacade roleFacade;
	
	@PostMapping("/membership")
	ResponseEntity<?> newRole(@RequestBody RoleMembershipDTO roleMembership) {
		try {
			roleFacade.createMembership(roleMembership);
			return ResponseEntity.ok().build();
		} catch (RolesAPIException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/membership")
	ResponseEntity<?> getRole(@RequestBody RoleMembershipDTO roleMembership) {
		try {
			Role role = roleFacade.findRole(roleMembership);
			return ResponseEntity.ok(role);
		} catch (RolesAPIException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/membership/{roleName}")
	ResponseEntity<?> get(@PathVariable String roleName) {
		try {
			List<RoleMembershipDTO> members = roleFacade.findMembership(roleName);
			return ResponseEntity.ok(members);
		} catch (RolesAPIException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/membership")
	void delete(@RequestBody RoleMembershipDTO roleMembership) {
		roleFacade.deleteMembership(roleMembership);
	}

}
