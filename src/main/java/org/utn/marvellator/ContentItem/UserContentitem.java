package org.utn.marvellator.ContentItem;

import java.util.Date;

/**
 * Created by Marcos on 7/4/2016.
 */
public class UserContentitem {

	private String id;

	private String name;

	private String userName;

	private String email;

	private Date lastLogin;

	private int numberOfFavorites;

	private int numberOfGroups;

	public UserContentitem() {
	}

	public UserContentitem(String id, String name, String userName, String email, Date lastLogin, int numberOfFavorites, int numberOfGroups) {
		this.setId(id);
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.lastLogin = lastLogin;
		this.setNumberOfFavorites(numberOfFavorites);
		this.setNumberOfGroups(numberOfGroups);
	}

	public UserContentitem(String userName) {
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getNumberOfFavorites() {
		return numberOfFavorites;
	}

	public void setNumberOfFavorites(int numberOfFavorites) {
		this.numberOfFavorites = numberOfFavorites;
	}

	public int getNumberOfGroups() {
		return numberOfGroups;
	}

	public void setNumberOfGroups(int numberOfGroups) {
		this.numberOfGroups = numberOfGroups;
	}

	public void setId(String id) {
		this.id = id;
	}
}
