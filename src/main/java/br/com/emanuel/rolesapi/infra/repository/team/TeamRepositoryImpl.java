package br.com.emanuel.rolesapi.infra.repository.team;

import org.springframework.stereotype.Repository;

import br.com.emanuel.rolesapi.domain.team.Team;
import br.com.emanuel.rolesapi.domain.team.TeamRepository;
import br.com.emanuel.rolesapi.infra.integration.IntegrationRestRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TeamRepositoryImpl implements TeamRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Team findById(String teamId) {
		Team team = entityManager.find(Team.class, teamId);
		
		if (team == null) {
			team = requestById(teamId);
		}
		
		return team;
	}

	private Team requestById(String teamId) {
		String url = String.format("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams/%s", teamId);
		Team team = new IntegrationRestRequest().get(url, Team.class);
		
		if (team != null) {
			entityManager.persist(team);
		}
		
		return team;
	}
	
}
