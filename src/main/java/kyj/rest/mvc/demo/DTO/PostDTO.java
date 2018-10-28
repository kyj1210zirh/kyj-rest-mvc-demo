package kyj.rest.mvc.demo.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO implements Serializable{
	//컨트롤러에서 DB로 데이터 전달을 위한 모델
	private static final long serialVersionUID = -7646575409829097310L;
	private int id;
	private String userId;
	private String userName;
	private String password;
	private String title;
	private String content;
	private int views;
	private Date regDate;
}
