package servlet;

public class TimeItem {
	private String Name;
	private String Supervisor;
	private String Observer;
	private String Examiner;
	private String date;
	private String time;
	private String room;
	
	/**
	 * Set methods for timetable item
	 */
	
	public boolean setName(String Name){
		this.Name = Name;
		return true;
	}
	
	public String getName(){
		return Name;
	}
	
	public boolean setSupervisor(String Supervisor){
		this.Supervisor = Supervisor;
		return true;
	}
	
	public String getSupervisor(){
		return Supervisor;
	}
	
	public boolean setObserver(String Observer){
		this.Observer = Observer;
		return true;
	}
	
	public String getObserver(){
		return Observer;
	}
	
	public boolean setExaminer(String Examiner){
		this.Examiner = Examiner;
		return true;
	}
	
	public String getExaminer(){
		return Examiner;
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
