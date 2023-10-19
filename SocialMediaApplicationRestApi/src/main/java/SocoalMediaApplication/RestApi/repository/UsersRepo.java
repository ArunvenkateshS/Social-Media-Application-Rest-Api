package SocoalMediaApplication.RestApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SocoalMediaApplication.RestApi.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	List<Users> findAll();

}
