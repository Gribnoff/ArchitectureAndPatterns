package ru.gribnoff.persistnce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gribnoff.persistnce.entities.util.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Track extends PersistableEntity {
	private String title;
//	@ManyToOne
	@JsonBackReference
	private Album album;
}
