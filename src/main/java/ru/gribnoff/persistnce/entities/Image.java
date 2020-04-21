package ru.gribnoff.persistnce.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.gribnoff.persistnce.entities.util.PersistableEntity;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
//@Entity
@EqualsAndHashCode(callSuper = true)
public class Image extends PersistableEntity implements Serializable {
	private String name;
}
