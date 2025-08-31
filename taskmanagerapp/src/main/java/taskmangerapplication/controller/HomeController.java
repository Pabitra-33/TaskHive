package taskmangerapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// Mapping for Welcome Page
    @GetMapping("/")
    public String welcomePage() {
        return "welcome"; // This will load templates/welcome.html
    }
}