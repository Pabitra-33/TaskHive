package taskmangerapplication.binding;

public class TaskBind {

//	private Integer id;
	private String taskname;
	private String description;
	private String timetocompletetask;
	
	// getter and setter methods
	//commenting the id field
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimetocompletetask() {
		return timetocompletetask;
	}
	public void setTimetocompletetask(String timetocompletetask) {
		this.timetocompletetask = timetocompletetask;
	}
}