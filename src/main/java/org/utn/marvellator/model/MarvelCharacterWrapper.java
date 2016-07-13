package org.utn.marvellator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarvelCharacterWrapper {

    @JsonProperty("marvelId")
    private String marvelId;

		@JsonProperty("name")
		private String name;

    public String getMarvelId() {
        return marvelId;
    }

	public String getName() {
		return name;
	}
}
