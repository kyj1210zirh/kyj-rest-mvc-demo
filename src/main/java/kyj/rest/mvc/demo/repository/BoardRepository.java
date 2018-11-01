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

	@Query("SELECT p FROM PostEntity p WHERE p.userName LIKE %:serachWord%")
	Page<PostEntity> findAllByUserName(@Param("serachWord") String searchWord, Pageable pageable);
}
