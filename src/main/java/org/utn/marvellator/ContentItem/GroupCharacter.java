package org.utn.marvellator.ContentItem;

import org.json.JSONObject;
import org.utn.marvellator.model.MarvelCharacter;

/**
 * Created by Marcos on 7/4/2016.
 */
public class GroupCharacter {

	private String marvelId;
	private String name = "";
	private String imageUrl = "";
	private boolean existsInGroup;


	public String getMarvelId() {
		return marvelId;
	}

	public static MarvelCharacter fromJson(JSONObject jsonObj){

		MarvelCharacter character = new MarvelCharacter();

		character.setMarvelId(String.valueOf(jsonObj.get("id")));
		character.setName((String) jsonObj.get("name"));

		return character;
	}

	public void setMarvelId(String marvelId) {
		this.marvelId = marvelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GroupCharacter(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "Character: '"+ name + "', marvelId: '"+ marvelId + "'";
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((MarvelCharacter) obj).getName());
	}
	public GroupCharacter(){

	}

	public GroupCharacter(String name, String marvelId){
		this.marvelId = marvelId;
		this.name = name;
	}

	public GroupCharacter(String name, String marvelId, boolean existsInGroup){
		this.marvelId = marvelId;
		this.name = name;
		setExistsInGroup(existsInGroup);
	}

	public boolean isExistsInGroup() {
		return existsInGroup;
	}

	public void setExistsInGroup(boolean existsInGroup) {
		this.existsInGroup = existsInGroup;
	}
}
