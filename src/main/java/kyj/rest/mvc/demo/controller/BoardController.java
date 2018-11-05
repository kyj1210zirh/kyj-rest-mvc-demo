package kyj.rest.mvc.demo.controller;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kyj.rest.mvc.demo.request.PostRequestModel;
import kyj.rest.mvc.demo.response.AjaxResponseBody;
import kyj.rest.mvc.demo.response.PasswordNotCorrectException;
import kyj.rest.mvc.demo.service.BoardService;

@Controller
@RequestMapping(path="/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//게시글 리스트 페이지로
	@GetMapping(path="/list")
	public String showBoard(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "20") int limit) {
		model.addAttribute("posts", boardService.getPosts(page, limit));
		return "board-list";
	}
	
	//게시글 조회 페이지로
	@GetMapping(path="/PostDetails/{id}")
	public String PostDetails(Model model, @PathVariable(value = "id") int id) {
		PostRequestModel postModel = boardService.getPost(id);
		model.addAttribute("PostRequestModel", postModel);
		return "board-PostDetails";
	}
	
	//게시글 쓰기 페이지로
	@GetMapping(path="/writePost")
	public String writePost(Model model) {
		model.addAttribute("PostRequestModel", new PostRequestModel());
		return "board-writePost";
	}
	
	//게시글 쓰기
	@PostMapping(path="/writePost", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String WritePost(@ModelAttribute("PostRequestModel")PostRequestModel postModel){
		boardService.writePost(postModel);
		return "redirect:/board/list";
	}
	
	//게시글 검색
	@PostMapping(path="/search")
	public String serachPosts(Model model
			, @RequestParam(value = "page", defaultValue = "0") int page
			, @RequestParam(value = "limit", defaultValue = "20") int limit
			, @RequestParam(value = "searchWord") String searchWord
			, @RequestParam(value = "searchOption") String searchOption) {
		model.addAttribute("posts", boardService.getPosts(page, limit, searchOption, searchWord));
		return "board-list";
	}
	
	//게시글 수정
	@Transactional
	@PutMapping(path="/PostDetails/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String modifyPost(@PathVariable(value = "id") int id, @ModelAttribute("PostRequestModel")PostRequestModel postModel) {
		boardService.writePost(postModel);
		return "redirect:/board/list";
	}
	
	//게시글 삭제
	@Transactional
	@DeleteMapping(path="/PostDetails/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String deletePost(@PathVariable(value = "id") int id, @ModelAttribute("PostRequestModel")PostRequestModel postModel) {
		boardService.deletePost(postModel);
		return "redirect:/board/list";
	}
	
	//게시글 수정/삭제 비밀번호 검증(Ajax)
	@PostMapping(path="/postPassChk", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> chkPostPassViaAjax(@Valid @RequestBody Map<String, Object> param, Errors errors){
		AjaxResponseBody result = new AjaxResponseBody();
		
		//If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
            result.setResult(false);
            
            return ResponseEntity.badRequest().body(result);
        }
		//PostRequestModel postModel = boardService.getPost(Integer.parseInt((String) param.get("id")));
		result = boardService.chkPostPassword(Integer.parseInt((String) param.get("id")), (String)param.get("pass"));
        
        return ResponseEntity.ok(result);
	}
	
	@ExceptionHandler(value= {PasswordNotCorrectException.class})
	public ModelAndView exceptionHandler(Exception ex, Locale locale) {
		return null;
	}
}