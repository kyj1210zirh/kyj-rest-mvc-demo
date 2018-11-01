package kyj.rest.mvc.demo.DTO;

import java.io.Serializable;

import kyj.rest.mvc.demo.security.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO  implements Serializable{
	private static final long serialVersionUID = 7554682770365409027L;
	private long id;
    private String userId;
    private String userName;
    private String password;
    private String encryptedPassword;
    private Role role;
}
