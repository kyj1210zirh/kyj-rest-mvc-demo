package kyj.rest.mvc.demo.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kyj.rest.mvc.demo.DTO.PostDTO;
import kyj.rest.mvc.demo.entity.PostEntity;
import kyj.rest.mvc.demo.request.PostRequestModel;
import kyj.rest.mvc.demo.service.BoardService;

@Controller
@RequestMapping(path="/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public ModelAndView showBoard(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "20") int limit) {
		model.addAttribute("PostRequestModel", new PostRequestModel());
		model.addAttribute("posts", boardService.getPosts(page, limit));
		return new ModelAndView("board-list");
	}
	
	@PostMapping(path="/writePost", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String WritePost(@ModelAttribute("PostRequestModel")PostRequestModel postModel){
		boardService.writePost(new ModelMapper().map(postModel, PostEntity.class));
		return "redirect:/board/list";
	}
}