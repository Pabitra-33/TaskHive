package taskmangerapplication.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import taskmangerapplication.binding.LoginUser;
import taskmangerapplication.binding.UserBind;
import taskmangerapplication.dao.UserDao;
import taskmangerapplication.model.UserEntity;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;//to perform user entity related operations.

	
	@GetMapping("/showform")
	public String showregisterForm(Model model) {
		model.addAttribute("user", new UserBind());
		return "register";
	}

	@PostMapping("/saveuser")
	public String registerUser(UserBind user, Model model) {

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
	public String loginUser(LoginUser user, Model model, HttpSession session) {
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
	
	@GetMapping("/signout")
	public String logout(HttpSession session, Model model) {
		
		session.invalidate();//deleting the sesion
		model.addAttribute("msg", "Logout successfull!!!");
		model.addAttribute("user", new LoginUser());
		return "login";
	}
}