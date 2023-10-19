package SocoalMediaApplication.RestApi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SocoalMediaApplication.RestApi.Error.NoSuchUserException;
import SocoalMediaApplication.RestApi.Service.ServiceClass;
import SocoalMediaApplication.RestApi.model.Posts;
import SocoalMediaApplication.RestApi.model.Users;
import SocoalMediaApplication.RestApi.repository.PostsRepo;
import SocoalMediaApplication.RestApi.repository.UsersRepo;
import jakarta.validation.Valid;

@RestController
public class UserController {
	
	UsersRepo usersRepo;
	ServiceClass service;
	PostsRepo postsRepo;
	
	public UserController(UsersRepo usersRepo, ServiceClass service
							, PostsRepo postsRepo) {
		super();
		this.usersRepo = usersRepo;
		this.service = service;
		this.postsRepo = postsRepo;
	}


	@GetMapping(path="/Users")
	public List<Users> getUsers() {
		return usersRepo.findAll();
	}
	
	@GetMapping("/Users/{userId}")
	public Users getUserById(@PathVariable int userId){
		if (service.findUserById(userId) == null) {
			throw new NoSuchUserException("No Such User Id :"+ userId);
		}
		return service.findUserById(userId);
	}
	
	
	@PostMapping("/Users")
	public ResponseEntity<Object> postUsers(@Valid @RequestBody Users users){
		Users savedUser = usersRepo.save(users);
	
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(savedUser.getUserId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/Users/{userId}")
	public void deleteUsers(@PathVariable int userId) {
		usersRepo.deleteById(userId);
	}
	
	
	@PutMapping("/Users/{userId}")
	public ResponseEntity<Object> updateUser(@RequestBody Users users) {
		usersRepo.save(users);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(null).build()
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
