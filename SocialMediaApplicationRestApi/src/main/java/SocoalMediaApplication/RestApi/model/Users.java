package SocoalMediaApplication.RestApi.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Users {

	@Id
	@JsonProperty("User Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@JsonProperty("User Name")
	@Size(min=2 , message = "Name should have atleast 2 character")
	private String name;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@JsonProperty("Date Of Birth")
	@Past(message = "Dob must be in past")
	private LocalDate dob;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "fk_id")
	private List<Posts> posts;
	
	
	public Users () {
		
	}
	
	public Users(int userId, String name, LocalDate dob) {
		this.userId = userId;
		this.name = name;
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", dob=" + dob + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}
	
	
}
