package br.com.emanuel.rolesapi.infra.repository.user;

import org.springframework.stereotype.Repository;

import br.com.emanuel.rolesapi.domain.user.User;
import br.com.emanuel.rolesapi.domain.user.UserRepository;
import br.com.emanuel.rolesapi.infra.integration.IntegrationRestRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findById(String userId) {
		
		User user = entityManager.find(User.class, userId);
		
		if (user == null) {
			user = requestById(userId);
		}
		
		return user;
	}

	private User requestById(String teamId) {
		
		String url = String.format("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users/%s", teamId);
		
		User user = new IntegrationRestRequest().get(url, User.class);
		if (user != null) {
			entityManager.persist(user);
		}
		
		return user;
	}

}
