package SocoalMediaApplication.RestApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import SocoalMediaApplication.RestApi.model.Posts;
import SocoalMediaApplication.RestApi.model.Users;
import SocoalMediaApplication.RestApi.repository.PostsRepo;
import SocoalMediaApplication.RestApi.repository.UsersRepo;


@Component
public class ServiceClass {
	
	UsersRepo usersRepo;
	PostsRepo postRepo;
	
	public ServiceClass(UsersRepo usersRepo,PostsRepo postRepo) {
		super();
		this.usersRepo = usersRepo;
		this.postRepo = postRepo;
	}



	public Users findUserById(int userId) {
		Optional<Users> list = usersRepo.findById(userId);
		Predicate<? super Users> predicate = user -> user.getUserId() == userId;
		return list.stream().filter(predicate ).findFirst().orElse(null);
	}

	public Posts findPostByPostId(int postId) {
		Optional<Posts> list = postRepo.findById(postId);
		Predicate<? super Posts> predicate = post -> post.getPostId() == postId;
		return list.stream().filter(predicate ).findFirst().orElse(null);
	}

	
	
}
