package kyj.rest.mvc.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import kyj.rest.mvc.demo.DTO.PostDTO;
import kyj.rest.mvc.demo.entity.PostEntity;
import kyj.rest.mvc.demo.request.PostRequestModel;

public interface BoardService{
	Page<PostEntity> getPosts(int page, int limit);
	PostRequestModel getPost(int id);
	void writePost(PostRequestModel Post);
	
}
