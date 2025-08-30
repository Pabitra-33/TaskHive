package taskmangerapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import taskmangerapplication.dao.TaskDao;

@Controller
public class TaskController {

	@Autowired
	private TaskDao taskDao;
}