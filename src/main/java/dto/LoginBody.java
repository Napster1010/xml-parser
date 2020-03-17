package dto;

import com.google.gson.annotations.Expose;

public class LoginBody {
	@Expose
	private String username;
	
	@Expose
	private String password;

	public LoginBody(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}	
}
