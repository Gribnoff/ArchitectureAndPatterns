package ru.gribnoff.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Image implements Serializable {
	private static final long serialVersionUID = -453687134884087053L;

	private long id;
	private String name;
}
