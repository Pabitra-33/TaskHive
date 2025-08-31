package taskmangerapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String taskname;
	private String description;
	private String timetocompletetask;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity; // each task belongs to a user
	//our requirement is one user can have more than one task so we used ManyToOne mapping.

	
	//getters and setters method
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}