package servlet;

public class AssessmentItem {
	private String name;
	private double percent;
	private int id;
	private String supervisor;
	private String observer;
	private String examiner;
	
	public boolean setName(String name,int id){
		this.name = name;
		this.id = id;
		return true;
	}
	
	public String getName(int id){
		if(id == this.id){
			return name;
		}else{
			return null;

		}
	}
	
	public boolean setPercentage(double percent,int id){
		this.percent = percent;
		this.id = id;
		return true;
	}
	
	public double getPercentage(int id){
		if(id == this.id){
			return percent;
		}else{
			return 0.0;
	
		}
	}
	
	public boolean setSupervisor(String supervisor,int id){
		this.supervisor = supervisor;
		this.id = id;
		return true;
	}
	
	public String getSupervisor(int id){
		if(id == this.id){
			return supervisor;
		}else{
			return null;
		}
	}
	
	public boolean setObserver(String observer,int id){
		this.observer = observer;
		this.id = id;
		return true;
	}
	
	public String getObserver(int id){
		if(id == this.id){
			return observer;
		}else{
			return null;
		}
	}
	
	public boolean setExaminer(String examiner,int id){
		this.examiner = examiner;
		this.id = id;
		return true;
	}
	
	public String getExaminer(int id){
		if(id == this.id){
			return examiner;
		}else{
			return null;
		}
	}
}
