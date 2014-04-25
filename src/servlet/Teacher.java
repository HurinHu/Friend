package servlet;

public class Teacher {
	private String name;
	
	/**
	 * Set methods for teacher
	 */
	
	public boolean setName(String name){
		this.name = name;
		return true;
	}
	
	public String getName(){
		return name;
	}
}
