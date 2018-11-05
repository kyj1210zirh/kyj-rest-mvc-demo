package kyj.rest.mvc.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;

import kyj.rest.mvc.demo.entity.PostEntity;
import kyj.rest.mvc.demo.request.PostRequestModel;
import kyj.rest.mvc.demo.response.AjaxResponseBody;

public interface BoardService{
	Page<PostEntity> getPosts(int page, int limit);
	Page<PostEntity> getPosts(int page, int limit, String searchOption, String searchWord);
	PostRequestModel getPost(int id);
	void writePost(PostRequestModel postModel);
	void deletePost(PostRequestModel postModel);
	AjaxResponseBody chkPostPassword(int id, String pass);
}
