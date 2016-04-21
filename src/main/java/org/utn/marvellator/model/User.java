package org.utn.marvellator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tomas on 4/20/2016.
 */
@Document
public class User {

	@Id
	private String id;
	private String nombre;

	public User(String name) {
		this.nombre = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
