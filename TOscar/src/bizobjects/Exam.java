package bizobjects;

public class Exam {
	
	private String name;
	
	private String status = "INC";

	public Exam(String name) {
		super();
		this.name = name;
	}
	
	public Exam(String name, String status) {
		super();
		this.name = name;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
