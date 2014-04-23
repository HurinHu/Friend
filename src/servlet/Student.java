package servlet;

public class Student {
	private int num;
	private String id;
	private String name;
	private String tel;
	private String project;
	private String supervisor;
	private String observer;
	private String examiner;
	private String date;
	private String time;
	private String room;
	
	public boolean setNum(int num){
		this.num = num;
		return true;
	}
	
	public int getNum(){
		return num;
	}
	
	public boolean setId(String id){
		this.id = id;
		return true;
	}
	
	public String getId(){
		return id;
	}
	
	public boolean setName(String name){
		this.name = name;
		return true;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean setTel(String tel){
		this.tel = tel;
		return true;
	}
	
	public String getTel(){
		return tel;
	}
	
	public boolean setProject(String project){
		this.project = project;
		return true;
	}
	
	public String getProject(){
		return project;
	}
	
	public boolean setSupervisor(String supervisor){
		this.supervisor = supervisor;
		return true;
	}
	
	public String getSupervisor(){
		return supervisor;
	}
	
	public boolean setObserver(String observer){
		this.observer = observer;
		return true;
	}
	
	public String getObserver(){
		return observer;
	}
	
	public boolean setExaminer(String examiner){
		this.examiner = examiner;
		return true;
	}
	
	public String getExaminer(){
		return examiner;
	}
	
	public boolean setDate(String date){
		this.date = date;
		return true;
	}
	
	public String getDate(){
		return date;
	}
	
	public boolean setTime(String time){
		this.time = time;
		return true;
	}
	
	public String getTime(){
		return time;
	}
	
	public boolean setRoom(String room){
		this.room = room;
		return true;
	}
	
	public String getRoom(){
		return room;
	}
}
