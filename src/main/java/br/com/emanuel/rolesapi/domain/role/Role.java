package br.com.emanuel.rolesapi.domain.role;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role {
	
	@Id
    @GeneratedValue
    @Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	public Role(String name) {
		this();
		this.name = name;
	}
	
	public Role() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void updateNotNull(Role newData) {
		Optional.ofNullable(newData.getName()).ifPresent(p -> setName(p));
	}

	public void update(Role newData) {
		setName(newData.getName());
	}

}
