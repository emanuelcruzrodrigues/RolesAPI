package br.com.emanuel.rolesapi.domain.role;

public class RoleMembershipDTO {
	
	private String roleName;
	private String userId;
	private String teamId;
	
	public RoleMembershipDTO(String roleName, String userId, String teamId) {
		this();
		this.roleName = roleName;
		this.userId = userId;
		this.teamId = teamId;
	}
	
	public RoleMembershipDTO() {
		super();
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
