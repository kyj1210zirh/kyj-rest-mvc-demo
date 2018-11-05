package kyj.rest.mvc.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kyj.rest.mvc.demo.entity.PostEntity;
import kyj.rest.mvc.demo.repository.BoardRepository;
import kyj.rest.mvc.demo.request.PostRequestModel;
import kyj.rest.mvc.demo.response.AjaxResponseBody;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	Utils utils;

	@Autowired
	BoardRepository boardRepository;

	@Override
	public Page<PostEntity> getPosts(int page, int limit) {
		if (page > 1) {
			page -= 1;
		}
		// 현 페이지와 보여줄 게시물 수, 정렬옵션 지정
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
		// List<Post> posts = boardRepository.findAll(pageableRequest).getContent();
		return boardRepository.findAll(pageableRequest);
	}

	@Override
	public Page<PostEntity> getPosts(int page, int limit, String searchOption, String searchWord) {
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
		Page<PostEntity> posts = null;
		switch (Integer.parseInt(searchOption)) {
		// 제목
		case 1: 
			posts = boardRepository.findBytitleContaining(searchWord, pageableRequest);
			break;
		// 내용
		case 2:
			posts = boardRepository.findBycontentContaining(searchWord, pageableRequest);
			break;
		// 제목 + 내용
		case 3:
			//posts = boardRepository.findBytitleContainingOrcontentContaining(searchWord, pageableRequest);
			break;
		// 작성자
		case 4:
			posts = boardRepository.findByuserNameContaining(searchWord, pageableRequest);
			break;
		}
		return posts;
	}

	@Override
	public void writePost(PostRequestModel postModel) {
		PostEntity post = new ModelMapper().map(postModel, PostEntity.class);
		boardRepository.save(post);
	}

	@Override
	public PostRequestModel getPost(int id) {
		PostEntity post = boardRepository.findById(id).get();
		boardRepository.addViews(post.getId());
		post.setPassword(null);
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
		// find By Id
		// SELTCT WHERE id
		PostEntity post = boardRepository.findById(id).get();
		// find By UserName AND UserPasswod
		AjaxResponseBody result = new AjaxResponseBody();
		if (post.getPassword().equals(pass)) {
			result.setMsg("Password correct!");
			result.setResult(true);
		} else {
			result.setMsg("Password incorrect! check your password.");
			result.setResult(false);
		}
		return result;
	}
}
