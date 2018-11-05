package kyj.rest.mvc.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import kyj.rest.mvc.demo.entity.PostEntity;

@Repository
public interface BoardRepository extends JpaRepository<PostEntity, Integer>{
	
	@Transactional
	@Modifying
	@Query("UPDATE PostEntity p SET p.views = p.views + 1 WHERE p.id = :id")
	void addViews(@Param("id") int id);

	Page<PostEntity> findBytitleContaining(String searchWord, Pageable pageableRequest);
	Page<PostEntity> findBycontentContaining(String searchWord, Pageable pageableRequest);
	//Page<PostEntity> findBytitleOrcontentContaining(String searchWord, Pageable pageableRequest);
	//SELECT * FROM posts WHERE user_name LIKE %?% ORDER BY id DESC limit ?
	Page<PostEntity> findByuserNameContaining(String searchWord, Pageable pageable);	
}
