package br.com.emanuel.rolesapi.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	@Id
    @Column(name="ID")
	private String id;
	
	@Column(name="DISPLAY_NAME")
	private String displayName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
