package taskmangerapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taskmangerapplication.model.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	
	//custom method to find a user by name
	UserEntity findByName(String username);
}