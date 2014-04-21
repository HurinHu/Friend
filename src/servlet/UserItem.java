package servlet;

public class UserItem {
	private String name = null;
	private String password = null;
	private String email = null;
	
	public boolean setName(String name){
		this.name = name;
		return true;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean setPassword(String password){
		this.password = password;
		return true;
	}
	
	public String getPassword(){
		return password;
	}
	
	public boolean setEmail(String email){
		this.email = email;
		return true;
	}
	
	public String getEmail(){
		return email;
	}
}
