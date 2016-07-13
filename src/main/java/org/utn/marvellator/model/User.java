package org.utn.marvellator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.*;

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

	private List<MarvelCharacter> favorites = new ArrayList<>();

	private Date lastLogin;

	public User() {
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

	/**
	 *
	 * @return returns all favorites
     */
	public List<MarvelCharacter> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<MarvelCharacter> favorites) {
		this.favorites = favorites;
	}

	/**
	 *
	 * @param character character that wants to add
	 * @throws CharacterAlreadyFavoritedException
     */
	public void addFavorite(MarvelCharacter character) throws CharacterAlreadyFavoritedException {
		checkAlreadyFavorited(character);
		favorites.add(character);
	}

	public void removeFavorite(MarvelCharacter character) {
		int i = 0;
		int index = 0;
		for (MarvelCharacter ch : favorites){
			if (ch.getMarvelId().equals(character.getMarvelId()) && ch.getName().equals(character.getName())){
				index = i;
				break;
			}
			i++;
		}
		favorites.remove(index);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @param character character that wants to be checked if repeated
	 * @throws CharacterAlreadyFavoritedException
	 */
	private void checkAlreadyFavorited(MarvelCharacter character) throws CharacterAlreadyFavoritedException {
		if (favorites.contains(character)) {
			throw new CharacterAlreadyFavoritedException(character);
		}
	}

	public Boolean hasFavorite(MarvelCharacter character){
		return favorites.contains(character);
	}

	public String statusOfCharacter(MarvelCharacter character){
		return hasFavorite(character)? "Favorited" : "Not favorited";
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
}
