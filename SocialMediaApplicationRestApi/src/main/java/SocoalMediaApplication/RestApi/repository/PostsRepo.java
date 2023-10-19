package SocoalMediaApplication.RestApi.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SocoalMediaApplication.RestApi.model.Posts;

public interface PostsRepo extends JpaRepository<Posts, Integer>{

	
	@Query(value = "select * from posts where fk_id = ?1",nativeQuery = true)
	List<Posts> findbyfk_id(int userId);
	
}
