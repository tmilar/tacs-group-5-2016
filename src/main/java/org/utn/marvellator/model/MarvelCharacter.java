package org.utn.marvellator.model;

/**
 * Created by Admin on 22/04/2016.
 */
public class MarvelCharacter {

    private String marvelId;
    private String name = "";

    public String getMarvelId() {
        return marvelId;
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
