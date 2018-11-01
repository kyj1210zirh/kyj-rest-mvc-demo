package kyj.rest.mvc.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import kyj.rest.mvc.demo.security.Role;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class UserEntity {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String userId;

	@Column(nullable=false, length=120)
	private String userName;
	
	@Column(nullable=false)
	private String encryptedPassword;

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
}
