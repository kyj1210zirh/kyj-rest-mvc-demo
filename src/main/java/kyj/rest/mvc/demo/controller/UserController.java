package kyj.rest.mvc.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kyj.rest.mvc.demo.DTO.UserDTO;
import kyj.rest.mvc.demo.request.UserLoginRequestModel;
import kyj.rest.mvc.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping(path="/login")
	public String createUser(@RequestBody UserLoginRequestModel userDetails) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class); 
		Boolean result = userService.createUser(userDTO);
		
		return null;
	}
}
