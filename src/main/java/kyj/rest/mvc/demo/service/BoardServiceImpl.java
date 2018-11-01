package kyj.rest.mvc.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kyj.rest.mvc.demo.DTO.PostDTO;
import kyj.rest.mvc.demo.entity.PostEntity;
import kyj.rest.mvc.demo.repository.BoardRepository;
import kyj.rest.mvc.demo.request.PostRequestModel;
import kyj.rest.mvc.demo.response.AjaxResponseBody;
import kyj.rest.mvc.demo.response.PasswordNotCorrectException;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	Utils utils;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public Page<PostEntity> getPosts(int page, int limit) {
		if(page>1) {
			page-=1;
		}
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
		//List<Post> posts =  boardRepository.findAll(pageableRequest).getContent();
		return boardRepository.findAll(pageableRequest);
	}

	@Override
	public Page<PostEntity> getPosts(String searchWord, int page, int limit) {
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
		return boardRepository.findAllByUserName(searchWord, pageableRequest);
	}
	
	@Override
	public void writePost(PostRequestModel postModel){
		PostEntity post = new ModelMapper().map(postModel, PostEntity.class);
		boardRepository.save(post);
	}

	@Override
	public PostRequestModel getPost(int id) {
		PostEntity post = boardRepository.findById(id).get();
		boardRepository.addViews(post.getId());
		PostRequestModel postModel = new ModelMapper().map(post, PostRequestModel.class);
		return postModel;
	}

	@Override
	public void deletePost(PostRequestModel postModel) {
		PostEntity post = new ModelMapper().map(postModel, PostEntity.class);
		boardRepository.delete(post);
	}

	@Override
	public AjaxResponseBody chkPostPassword(int id, String pass) {
		//find		By		Id
		//SELTCT 	WHERE	id
		PostEntity post = boardRepository.findById(id).get();
		//find	By	UserName	AND	UserPasswod
		AjaxResponseBody result = new AjaxResponseBody();
		if(post.getPassword().equals(pass)) {
			result.setMsg("Password correct!");
        	result.setResult(true);
		} else {
        	result.setMsg("Password incorrect! check your password.");
        	result.setResult(false);
        }
		return result;
	}
}
