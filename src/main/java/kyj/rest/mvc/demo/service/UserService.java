package kyj.rest.mvc.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import kyj.rest.mvc.demo.DTO.UserDTO;

public interface UserService extends UserDetailsService{
	UserDTO getUser(int id);
	Boolean createUser(UserDTO userDTO);
}
