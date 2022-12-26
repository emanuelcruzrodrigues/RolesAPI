package br.com.emanuel.rolesapi.infra.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.rolesapi.domain.role.usecase.NewRole;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseStart {

	@Autowired
	private NewRole newRole;
	
	@PostConstruct
	public void setupBasicRoles() {
		newRole.createRole("Developer");
		newRole.createRole("Product Owner");
		newRole.createRole("Tester");
	}
	
}
