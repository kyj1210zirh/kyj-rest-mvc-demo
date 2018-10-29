package kyj.rest.mvc.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="posts")
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//로그인 기능 구현시 외래키 설정.
	//익명은 컬럼값이 null
	@Column
	private String userId;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	@NotEmpty
	@Column(nullable=false)
	private String title;
	
	@NotEmpty
	@Lob
	@Column(nullable=false)
	private String content;
	
	@Column(nullable=false, columnDefinition="int default 0", insertable=false)
	private int views;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, columnDefinition = "datetime default now()")
	private Date regDate = new Date();
}
