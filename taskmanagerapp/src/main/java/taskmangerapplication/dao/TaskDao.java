package taskmangerapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taskmangerapplication.model.TaskEntity;
import taskmangerapplication.model.UserEntity;

@Repository
public interface TaskDao extends JpaRepository<TaskEntity, Integer>{

	//custom method to return list of tasks for the user.
	List<TaskEntity> findByUserEntity(UserEntity userEntity);
}