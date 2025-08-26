package taskmangerapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskEntity {

	@Id
	private String taskname;
	private String timetocompletetask;
	
	@ManyToOne
	private UserEntity userEntity; 
	//our requirement is one user can have more than one task so we used ManyToOne mapping.

	//getters and setters method
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTimetocompletetask() {
		return timetocompletetask;
	}

	public void setTimetocompletetask(String timetocompletetask) {
		this.timetocompletetask = timetocompletetask;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}