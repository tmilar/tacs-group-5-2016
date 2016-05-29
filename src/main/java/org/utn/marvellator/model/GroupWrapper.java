package org.utn.marvellator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupWrapper {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
