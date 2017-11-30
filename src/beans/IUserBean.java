package beans;

import javax.ws.rs.PathParam;

import dto.UserDTO;

public interface IUserBean {
	public void logout(String email);
	public UserDTO login(String email,  String password);
	void register(String email, String password);
}
