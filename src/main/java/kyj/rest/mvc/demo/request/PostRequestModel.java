package kyj.rest.mvc.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestModel {
	//클라이언트에서 컨트롤러로 전달되는 데이터 모델
	private int id;
	private String userId; //익명일땐 자동생성
	private String userName;
	private String password;
	private String title;
	private String content;
}
