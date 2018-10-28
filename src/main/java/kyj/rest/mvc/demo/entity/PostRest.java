package kyj.rest.mvc.demo.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRest {
	//DB에서 클라이언트로 게시판 리스트를 보여주기위한 모델.
	private int id;
	private String userName;
	private String title;
	//private String content; 리스트이기때문에 내용은 필요없음
	private int views;
	private Date regDate;
}
