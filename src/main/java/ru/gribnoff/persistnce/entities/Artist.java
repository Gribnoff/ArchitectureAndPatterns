package ru.gribnoff.persistnce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ru.gribnoff.persistnce.entities.util.PersistableEntity;

import javax.persistence.*;
import java.util.UUID;

@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Artist extends PersistableEntity {
	private static final long serialVersionUID = -453687134884087053L;

//	@Id
//	@GeneratedValue
	private UUID id;

	private String name;

//	@OneToOne(cascade = CascadeType.ALL)
	private Image photo;

	private String description;
}
