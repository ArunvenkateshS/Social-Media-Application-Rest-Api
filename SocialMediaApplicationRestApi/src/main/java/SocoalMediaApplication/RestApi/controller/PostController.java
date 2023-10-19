package SocoalMediaApplication.RestApi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SocoalMediaApplication.RestApi.Error.NoSuchUserException;
import SocoalMediaApplication.RestApi.Service.ServiceClass;
import SocoalMediaApplication.RestApi.model.Posts;
import SocoalMediaApplication.RestApi.model.Users;
import SocoalMediaApplication.RestApi.repository.PostsRepo;
import SocoalMediaApplication.RestApi.repository.UsersRepo;

@RestController
public class PostController {

	UsersRepo usersRepo;
	ServiceClass service;
	PostsRepo postsRepo;
	
	public PostController(UsersRepo usersRepo, ServiceClass service
							, PostsRepo postsRepo) {
		super();
		this.usersRepo = usersRepo;
		this.service = service;
		this.postsRepo = postsRepo;
	}

	@GetMapping(path="/Posts")
	public List<Posts> getPosts() {
		return postsRepo.findAll();
	}
	
	@GetMapping(path="Posts/{postId}")
	public Posts getPost(@PathVariable int postId) {
		if (service.findPostByPostId(postId) == null) {
			throw new NoSuchUserException("No Such Post Id :"+ postId);
		}
		return service.findPostByPostId(postId);
	}
	
	@GetMapping("/Users/{userId}/Posts")
	public List<Posts> getUserPosts(@PathVariable int userId){
		
		if (postsRepo.findbyfk_id(userId) == null) {
			System.out.println("NO POST FOR THIS UNIQUE ID : " + userId);
			return postsRepo.findbyfk_id(userId);
		}
		else {
		return postsRepo.findbyfk_id(userId);
		}
	}
	
	@GetMapping(path="Users/{userId}/Posts/{postId}")
	public Posts getuserPost(@PathVariable int postId) {
		if (service.findPostByPostId(postId) == null) {
			throw new NoSuchUserException("No Such Post Id :"+ postId);
		}
		return service.findPostByPostId(postId);
	}

	@PostMapping("/Users/{userId}/Posts")
	public ResponseEntity<Object> postUserPosts(@PathVariable int userId,@RequestBody Posts post) {
		Users user = service.findUserById(userId);
		post.setUser(user);
		Posts save = postsRepo.save(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{postId}")
				.buildAndExpand(save.getPostId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/Users/{userId}/Posts/{postId}")
	public void deletePost(@PathVariable int postId) {
		postsRepo.deleteById(postId);
	}
	
	@PutMapping("/Users/{userId}/Posts/{postId}")
	public ResponseEntity<Object> updateUser(@RequestBody Posts post) {
		postsRepo.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(null).build()
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	
	
	
	
}