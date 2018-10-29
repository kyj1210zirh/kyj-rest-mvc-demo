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
import kyj.rest.mvc.demo.response.PasswordNotCorrectException;

@Service
public class BoardServiceImpl implements BoardService{
	
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
	public void writePost(PostRequestModel postModel){
		PostEntity post = new ModelMapper().map(postModel, PostEntity.class);
		if(post.getId()!=0 && post.getUserId()== null) {
			if(post.getPassword() == boardRepository.findById(post.getId()).get().getPassword()) {
				boardRepository.save(post);
			} else {
				throw new PasswordNotCorrectException("게시물의 패스워드가 맞지 않습니다.");
			}
		} else {
			boardRepository.save(post);
		}
	}

	@Override
	public PostRequestModel getPost(int id) {
		PostRequestModel post = new ModelMapper().map(boardRepository.findById(id).get(), PostRequestModel.class);
		return post;
	}
}
