package kyj.rest.mvc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kyj.rest.mvc.demo.entity.PostEntity;

@Repository
public interface BoardRepository extends JpaRepository<PostEntity, Long>{
	
}
