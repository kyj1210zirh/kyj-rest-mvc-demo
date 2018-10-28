package kyj.rest.mvc.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kyj.rest.mvc.demo.DTO.PostDTO;
import kyj.rest.mvc.demo.entity.PostEntity;
import kyj.rest.mvc.demo.repository.BoardRepository;
import kyj.rest.mvc.demo.request.PostRequestModel;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public Page<PostEntity> getPosts(int page, int limit) {
		Pageable pageableRequest = PageRequest.of(page, limit);
		//List<Post> posts =  boardRepository.findAll(pageableRequest).getContent();
		return boardRepository.findAll(pageableRequest);
	}

	@Override
	public void writePost(PostEntity Post) {
		PostEntity post = new ModelMapper().map(Post, PostEntity.class);
		boardRepository.save(post);
	}
}
