package org.utn.marvellator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
public class User {

	@Id
	private String id;

	private String name;

	@Indexed(unique = true)
	private String userName;

	private String password;

	private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

	private Set<Integer> favorites = new HashSet<Integer>();

	public User(){
	}

	public User(String name, String userName, String password) {
		this.name = name;
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public User(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public Set<Integer> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Integer> favorites) {
		this.favorites = favorites;
	}

	public void addFavorite(Integer characterId){
		this.favorites.add(characterId);
	}

	public void removeFavorite(Integer characterId){
		this.favorites.remove(characterId);
	}

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
