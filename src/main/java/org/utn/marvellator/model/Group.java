package org.utn.marvellator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "group")
public class Group {

	@Id
	private String id;

	private String name;

	private Set<Integer> characters = new HashSet<Integer>();

	private String creatorName;

	public Group(){
	}

	public Group(String name, String creatorName) {
		this.name = name;
		this.creatorName = creatorName;
	}

	public Group(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<Integer> characters) {
		this.characters = characters;
	}

	public void addCharacter(Integer characterId){
		this.characters.add(characterId);
	}

	public void removeCharacter(Integer characterId){
		this.characters.remove(characterId);
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
