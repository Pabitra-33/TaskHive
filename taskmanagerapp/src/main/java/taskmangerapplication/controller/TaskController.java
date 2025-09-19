package taskmangerapplication.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// ================== SAVE TASK ==================
	@PostMapping("/savetasks")
	public String saveTask(Model model, TaskBind task, HttpSession session, RedirectAttributes redirectAttributes) {
		
		String username = (String) session.getAttribute("username");//getting the user from the session
//		System.out.println(username);
		
		if (username == null) {
			redirectAttributes.addFlashAttribute("msg", "Session expired. Please login again.");
            return "login"; // redirect to login if no session is there.
        }
		
		UserEntity userentity = userDao.findUserByName(username);
		TaskEntity entity = new TaskEntity();
		
		entity.setUserEntity(userentity);//setting the tasks for the user entity.
		
		// copying form values from binding to entity
		BeanUtils.copyProperties(task, entity, "id");
		
		TaskEntity saved = taskDao.save(entity);
		
		if (saved != null) {
			redirectAttributes.addFlashAttribute("msg", "✅ Task added successfully!");
			return "home";
		} else {
			redirectAttributes.addFlashAttribute("msg", "❌ Something went wrong, try again.");
			return "addtask";
		}
	}

	
	// ================== VIEW TASKS ==================
	@GetMapping("/viewtasks")
	public String viewTasks(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		
		UserEntity entity = userDao.findUserByName(username);
		
		List<TaskEntity> tasks = taskDao.findByUserEntity(entity);
		
		model.addAttribute("tasks", tasks);
		return "viewtask";//triggering the view task web page
	}
	
	
	// ================== EDIT TASK ==================
	@GetMapping("/edittask/{id}")
	public String showEditTaskPage(@PathVariable("id") Integer id, Model model, HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        model.addAttribute("msg", "Session expired. Please login again.");
	        return "login";
	    }

	    TaskEntity task = taskDao.findById(id).orElse(null);
	    if (task == null) {
	        model.addAttribute("msg", "Task not found!");
	        return "redirect:/viewtasks";
	    }

	    model.addAttribute("task", task);
	    return "edittask"; // this will be your Thymeleaf page for editing
	}

	@PostMapping("/updatetask")
	public String updateTask(@ModelAttribute("task") TaskEntity task, HttpSession session, Model model) {
	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        model.addAttribute("msg", "Session expired. Please login again.");
	        return "login";
	    }

	    // keep the user reference intact
	    UserEntity user = userDao.findUserByName(username);
	    task.setUserEntity(user);

	    taskDao.save(task); // save updated task
	    return "redirect:/viewtasks";
	}


	// ================== DELETE TASK ==================
	@GetMapping("/deletetask/{id}")
	public String deleteTask(@PathVariable("id") Integer id, HttpSession session, Model model) {
	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        model.addAttribute("msg", "Session expired. Please login again.");
	        return "login";
	    }

	    taskDao.deleteById(id);
	    return "redirect:/viewtasks";
	}
}