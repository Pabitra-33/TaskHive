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
//		System.out.println("New task added");
		model.addAttribute("task", new TaskBind());
		return "addtask";
	}
	
	
	@PostMapping("/savetasks")
	public String saveTask(Model model, TaskBind task, HttpSession session) {
		
		String username = (String) session.getAttribute("username");//getting the user from the session
//		System.out.println(username);
		
		if (username == null) {
            model.addAttribute("msg", "Session expired. Please login again.");
            return "login"; // redirect to login if no session is there.
        }
		
		UserEntity userentity = userDao.findUserByName(username);
		TaskEntity entity = new TaskEntity();
		
		entity.setUserEntity(userentity);//setting the tasks for the user entity.
		
		// copying form values from binding and then to entity
		BeanUtils.copyProperties(task, entity, "id");
		
		TaskEntity saved = taskDao.save(entity);
		
		if (saved != null) {
			model.addAttribute("msg", "task saved");
			return "home";
		} else {
			 model.addAttribute("msg", "Failed to save task. Try again.");
			return "addtask";
		}
	}

	
	@GetMapping("/viewtasks")
	public String viewTasks(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		
		UserEntity entity = userDao.findUserByName(username);
		
		List<TaskEntity> tasks = taskDao.findByUserEntity(entity);
		
		model.addAttribute("tasks", tasks);
		return "viewtask";//triggering the view task web page
	}
}