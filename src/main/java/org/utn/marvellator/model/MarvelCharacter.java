package org.utn.marvellator.model;

/**
 * Created by Admin on 22/04/2016.
 */
public class MarvelCharacter {

    private String id;
    private String name = "";

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

    public MarvelCharacter(){
    }

    public MarvelCharacter(String name){
        this.name = name;
    }

}
