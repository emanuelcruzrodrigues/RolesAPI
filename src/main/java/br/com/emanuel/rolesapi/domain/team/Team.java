package br.com.emanuel.rolesapi.domain.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEAMS")
public class Team {
	
	@Id
    @Column(name="ID")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
