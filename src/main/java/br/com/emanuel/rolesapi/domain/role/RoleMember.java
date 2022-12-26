package br.com.emanuel.rolesapi.domain.role;

import br.com.emanuel.rolesapi.domain.team.Team;
import br.com.emanuel.rolesapi.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE_MEMBERS")
public class RoleMember {
	
	@Id
    @GeneratedValue
    @Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_ROLE")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "ID_TEAM")
	private Team team;
	
	public RoleMember(Role role, User user, Team team) {
		this();
		this.role = role;
		this.user = user;
		this.team = team;
	}
	
	public RoleMember() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

}
