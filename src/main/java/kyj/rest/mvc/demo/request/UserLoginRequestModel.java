package kyj.rest.mvc.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestModel {
	private String userName;
	private String password;
}
