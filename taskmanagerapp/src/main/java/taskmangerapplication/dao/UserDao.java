package taskmangerapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taskmangerapplication.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
	
	//custom method to find a user by name
	UserEntity findByName(String username);
}