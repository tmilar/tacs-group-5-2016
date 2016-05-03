package org.utn.marvellator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@CompoundIndex(name="name_compound_index", def="{ 'group_name':1, 'creator_name':1 }", unique = true)
@Document(collection = "group")
public class Group {

	@Id
	private String id;

	@Field("group_name")
	private String name;

	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();

	@Field("creator_name")
	private String creator;

	public Group(){
	}

	public Group(String name, String creator) {
		this.name = name;
		this.creator = creator;
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

	public List<MarvelCharacter> getCharacters() {
		return characters;
	}

	/**
	 * Add character to characters list without repeating (or else throw error)
	 *
	 * @param character
	 * @throws CharacterAlreadyInGroupException
     */
	public void addCharacter(MarvelCharacter character) throws CharacterAlreadyInGroupException {
		checkCharacterIsAlreadyInGroup(character);
		this.characters.add(character);
	}

	private void checkCharacterIsAlreadyInGroup(MarvelCharacter character) throws CharacterAlreadyInGroupException {
		if(characters.contains(character)){
			throw new CharacterAlreadyInGroupException(character, this);
		}
	}

	public boolean removeCharacter(MarvelCharacter character){
		return this.characters.remove(character);
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Group '"+name+"', created by: '"+ creator + "', characters: [" + characters.toString() + "]";
	}
}
