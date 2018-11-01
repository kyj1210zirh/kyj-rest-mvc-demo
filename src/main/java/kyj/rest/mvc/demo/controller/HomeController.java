package kyj.rest.mvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kyj.rest.mvc.demo.request.UserLoginRequestModel;

@Controller
public class HomeController {
	
	@GetMapping(path="/")
	public String goBoardList() {
		return "redirect:/board/list";
	}
	
	@GetMapping(path="/login")
	public ModelAndView showLoginForm() {
		return new ModelAndView("login", "loginModel", new UserLoginRequestModel());
	}
	
	@GetMapping("/access-denied")
	public String ShowAccessDenied() {
		return "access-denied";
	}
}
