package taskmangerapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taskmangerapplication.entity.TaskEntity;
import taskmangerapplication.entity.UserEntity;

@Repository
public interface TaskDao extends JpaRepository<TaskEntity, String>{

	//custom method to return list of tasks for the user.
	List<TaskEntity> findByUserEntity(UserEntity userEntity);
}