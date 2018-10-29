package kyj.rest.mvc.demo.controller;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kyj.rest.mvc.demo.request.PostRequestModel;
import kyj.rest.mvc.demo.response.PasswordNotCorrectException;
import kyj.rest.mvc.demo.service.BoardService;

@Controller
@RequestMapping(path="/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping(path="/list")
	public String showBoard(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "20") int limit) {
		model.addAttribute("posts", boardService.getPosts(page, limit));
		return "board-list";
	}
	
	@GetMapping(path="/PostDetails")
	public String PostDetails(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
		if(id!=0) {
			PostRequestModel postModel = boardService.getPost(id);
			postModel.setPassword(null);
			model.addAttribute("PostRequestModel", postModel);
		}else {
			model.addAttribute("PostRequestModel", new PostRequestModel());
		}
		return "board-PostDetails";
	}
	
	@RequestMapping(path="/writePost", method= {RequestMethod.POST, RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public String WritePost(@ModelAttribute("PostRequestModel")PostRequestModel postModel){
		boardService.writePost(postModel);
		return "redirect:/board/list";
	}
	
	@ExceptionHandler(value= {PasswordNotCorrectException.class})
	public ModelAndView exceptionHandler(Exception ex, Locale locale) {
		return null;
	}
	
}