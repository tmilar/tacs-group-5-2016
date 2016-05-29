package org.utn.marvellator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarvelCharacterWrapper {

    @JsonProperty("marvelId")
    private String marvelId;

    public String getMarvelId() {
        return marvelId;
    }
}
