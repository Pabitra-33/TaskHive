package taskmangerapplication.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import taskmangerapplication.binding.LoginUser;
import taskmangerapplication.binding.Task;
import taskmangerapplication.binding.User;
import taskmangerapplication.dao.TaskDao;
import taskmangerapplication.dao.UserDao;
import taskmangerapplication.entity.TaskEntity;
import taskmangerapplication.entity.UserEntity;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;//to perform user entity related operations.

	@Autowired
	private TaskDao taskDao;//to perform task entity related operations.

	
	@GetMapping("/showform")
	public String showregisterForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/saveuser")
	public String registerUser(User user, Model model) {

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		UserEntity save = userDao.save(entity);
		if (save != null) {
			System.out.println("Data saved!!! but login not triggerred!!!");
			model.addAttribute("user", new LoginUser());
			return "login";
		} else {
			model.addAttribute("msg", "Regsitration Failed Try Again!!!!!");
			return "register";
		}
	}

	@PostMapping("/login")
	public String login(LoginUser user, Model model, HttpSession session) {
		String username = user.getUsername();
		String pswd = user.getPassword();

		// lookup user from DB using our existing custom method
		UserEntity found = userDao.findByName(username);

		if (found != null && pswd.equals(found.getPswd())) {
			session.setAttribute("user", username);//adding the user in the session for further operation
			session.setAttribute("pswd", pswd);
			return "home";
		} else {
			model.addAttribute("user", new LoginUser());
			model.addAttribute("msg", "LOGIN FAILED!!!!!");
			return "login";
		}
	}
	

	@GetMapping("/tasks/new")
	public String showt(Model model) {
		System.out.println("New task added");
		model.addAttribute("task", new Task());
		return "task";
	}
	

	@PostMapping("/tasks")
	public String savetask(Model model, Task task, HttpSession session) {
		
		System.out.println("Hey there");
		String username = (String) session.getAttribute("user");
		System.out.println(username);
		UserEntity userentity = userDao.findByName(username);
		TaskEntity entity = new TaskEntity();
		entity.setUserEntity(userentity);
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
	public String viewtasks(HttpSession session, Model model) {

		String username = (String) session.getAttribute("user");
		UserEntity entity = userDao.findByName(username);
		List<TaskEntity> taks = taskDao.findByUserEntity(entity);
		model.addAttribute("tasks", taks);
		return "viewtask";
	}

	
	@GetMapping("/signout")
	public String logout(HttpSession session, Model model) {
		
		session.invalidate();
		model.addAttribute("msg", "Logout successfull!!!");
		model.addAttribute("user", new LoginUser());
		return "login";
	}
}