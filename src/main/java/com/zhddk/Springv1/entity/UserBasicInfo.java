package com.zhddk.Springv1.entity;

public class UserBasicInfo {
	
	private Integer userid;
	private String username;
	private String userpwd;
	private String usersex;
	private String userphone;
	private String useremail;
	private boolean useradmin;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsersex() {
		return usersex;
	}
	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public boolean isUseradmin() {
		return useradmin;
	}
	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}
	
	@Override
	public String toString() {
		return "UserBasicInfo [userid=" + userid + ", username=" + username + ", userpwd=" + userpwd + ", usersex="
				+ usersex + ", userphone=" + userphone + ", useremail=" + useremail + ", useradmin=" + useradmin + "]";
	}
	
	
	
	
	
}
