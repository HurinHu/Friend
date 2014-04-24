package servlet;

public class Teacher {
	private String name;
	
	public boolean setName(String name){
		this.name = name;
		return true;
	}
	
	public String getName(){
		return name;
	}
}
