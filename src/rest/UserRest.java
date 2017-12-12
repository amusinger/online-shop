package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.UserBean;
import dto.UserDTO;

@Path("/profile")
public class UserRest {

   @EJB
    UserBean userBean;
	    

    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/online")
	public List<UserDTO> isOnline() {
    		return userBean.isOnline();
    }
    
    public List<UserDTO> getUsers() {
		return userBean.getAllUsers();
}
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/register/{email}/password/{password}")
	public void register(@PathParam("email") String email,  @PathParam("password")String password) {
    		userBean.register(email, password);
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/login/{email}/password/{password}")
	public UserDTO login(@PathParam("email") String email,  @PathParam("password") String password) {
	    	System.out.println(email);
	    	System.out.println(password);
    		return userBean.login(email, password);
    }
    
	@Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/logout/{email}/")
	public void logout(@PathParam("email") String email) {
		userBean.logout(email);
//		return Response.ok((res == true ? "okey registered" : "failed register")).build();

	}
	
	
}
