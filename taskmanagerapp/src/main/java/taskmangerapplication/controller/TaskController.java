package taskmangerapplication.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import taskmangerapplication.binding.TaskBind;
import taskmangerapplication.dao.TaskDao;
import taskmangerapplication.dao.UserDao;
import taskmangerapplication.model.TaskEntity;
import taskmangerapplication.model.UserEntity;

@Controller
public class TaskController {

	@Autowired
	private UserDao userDao;//to perform user entity related operations.
	
	
	@Autowired
	private TaskDao taskDao;// to perform task entity related operations.
	
	
	@GetMapping("/tasks/new")
	public String addnewTask(Model model) {
		System.out.println("New task added");
		model.addAttribute("task", new TaskBind());
		return "task";
	}
	
	
	@PostMapping("/savetasks")
	public String saveTask(Model model, TaskBind task, HttpSession session) {
		
//		System.out.println("Hey there");
		String username = (String) session.getAttribute("user");//getting the user from the session
//		System.out.println(username);
		
		UserEntity userentity = userDao.findByName(username);
		TaskEntity entity = new TaskEntity();
		entity.setUserEntity(userentity);//setting the tasks for the user entity
		BeanUtils.copyProperties(task, entity);
		
		TaskEntity save = taskDao.save(entity);
		if (save != null) {
			model.addAttribute("msg", "task saved");
			return "home";
		} else {
			return "task";
		}
	}

	
	@GetMapping("/viewtasks")
	public String viewTasks(HttpSession session, Model model) {

		String username = (String) session.getAttribute("user");
		UserEntity entity = userDao.findByName(username);
		List<TaskEntity> taks = taskDao.findByUserEntity(entity);
		model.addAttribute("tasks", taks);
		return "viewtask";//triggering the view task web page
	}
}