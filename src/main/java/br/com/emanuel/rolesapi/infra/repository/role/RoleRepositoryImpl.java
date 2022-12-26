package br.com.emanuel.rolesapi.infra.repository.role;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.emanuel.rolesapi.domain.role.Role;
import br.com.emanuel.rolesapi.domain.role.RoleMember;
import br.com.emanuel.rolesapi.domain.role.RoleMembershipDTO;
import br.com.emanuel.rolesapi.domain.role.RoleRepository;
import br.com.emanuel.rolesapi.domain.team.Team;
import br.com.emanuel.rolesapi.domain.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class RoleRepositoryImpl implements RoleRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Role findByName(String roleName) {
		StringBuilder ql = new StringBuilder();
		ql.append(" select r from Role r ");
		ql.append(" where upper(r.name) = :roleName ");
		
		Query query = entityManager.createQuery(ql.toString());
		query.setParameter("roleName", roleName.toUpperCase());
		
		@SuppressWarnings("unchecked")
		List<Role> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public void save(Role role) {
		entityManager.persist(role);
	}

	@Override
	public Role findById(Long id) {
		Role role = entityManager.find(Role.class, id);
		return role;
	}

	@Override
	public List<Role> listAll() {
		StringBuilder ql = new StringBuilder();
		ql.append(" select r from Role r ");
		ql.append(" order by r.name ");
		
		Query query = entityManager.createQuery(ql.toString());
		
		@SuppressWarnings("unchecked")
		List<Role> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void delete(Role role) {
		entityManager.remove(role);
	}

	@Override
	public void save(RoleMember roleMember) {
		entityManager.persist(roleMember);
	}

	@Override
	public RoleMember findRoleMembership(User user, Team team, Role role) {
		StringBuilder ql = new StringBuilder();
		ql.append(" select rome from RoleMember rome ");
		
		ql.append(" where rome.user.id = :idUser ");
		
		ql.append(" and rome.team.id = :idTeam ");
		
		if (role != null) {
			ql.append(" and rome.role.id = :idRole ");
		}
		
		ql.append(" order by rome.id ");
		
		Query query = entityManager.createQuery(ql.toString());
		query.setParameter("idUser", user.getId());
		query.setParameter("idTeam", team.getId());
		
		if (role != null) {
			query.setParameter("idRole", role.getId());
		}
		
		@SuppressWarnings("unchecked")
		List<RoleMember> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public List<RoleMembershipDTO> findMembership(String roleName) {
		StringBuilder ql = new StringBuilder();
		ql.append(" select new br.com.emanuel.rolesapi.domain.role.RoleMembershipDTO ( ");
		ql.append("  rol.name ");
		ql.append(", rome.user.id ");
		ql.append(", rome.team.id ");
		ql.append(" ) ");
		
		
		ql.append(" from RoleMember rome ");
		ql.append(" inner join rome.role rol ");
		
		ql.append(" where upper(role.name) = :roleName ");
		ql.append(" order by rome.id ");
		
		Query query = entityManager.createQuery(ql.toString(), RoleMembershipDTO.class);
		query.setParameter("roleName", roleName.toUpperCase());
		
		@SuppressWarnings("unchecked")
		List<RoleMembershipDTO> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void delete(RoleMember storedRoleMember) {
		entityManager.remove(storedRoleMember);
	}

}
