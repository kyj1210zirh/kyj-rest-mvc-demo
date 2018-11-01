package kyj.rest.mvc.demo.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kyj.rest.mvc.demo.DTO.UserDTO;
import kyj.rest.mvc.demo.entity.UserEntity;
import kyj.rest.mvc.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUserName(username);
		if(userEntity==null)
			throw new UsernameNotFoundException(username);

		return new User(userEntity.getUserName(), userEntity.getEncryptedPassword(), true,
				true, true,
				true, new ArrayList<>());
	}

	@Override
	public UserDTO getUser(int id) {
		UserEntity userEntity = userRepository.findById((long) id).get();
		if(userEntity==null)
			throw new UsernameNotFoundException(String.valueOf(id));
		
		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public Boolean createUser(UserDTO userDTO) {
		if(userRepository.findByUserName(userDTO.getUserName()) != null) {
			throw new RuntimeException("User name is already exists.");
		}
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
		
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		
		userRepository.save(userEntity);
		
		return null;
	}
}
