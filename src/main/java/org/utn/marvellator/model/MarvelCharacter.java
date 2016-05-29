package org.utn.marvellator.model;

import org.json.JSONObject;

/**
 * Created by Admin on 22/04/2016.
 */
public class MarvelCharacter {

    private String marvelId;
    private String name = "";
    private String imageUrl = "";

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

    public MarvelCharacter(){
    }

    public MarvelCharacter(String name, String marvelId){
        this.marvelId = marvelId;
        this.name = name;
    }

    public MarvelCharacter(String name){
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
}
