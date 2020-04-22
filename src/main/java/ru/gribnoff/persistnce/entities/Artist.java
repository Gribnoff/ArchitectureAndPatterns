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
//@EqualsAndHashCode(callSuper = true)
public class Artist {
	private static final long serialVersionUID = -453687134884087053L;

//	@Id
//	@GeneratedValue
	private UUID id;

	private String name;

//	@OneToOne(cascade = CascadeType.ALL)
	private Image photo;

	private String description;

	private Artist() {
	}

	public static Builder newBuilder() {
		return new Artist().new Builder();
	}

	public class Builder {

		private Builder() {
		}

		public Builder setId(UUID id) {
			Artist.this.id = id;
			return this;
		}

		public Builder setName(String name) {
			Artist.this.name = name;
			return this;
		}

		public Builder setPhoto(Image photo) {
			Artist.this.photo = photo;
			return this;
		}

		public Builder setDescription(String description) {
			Artist.this.description = description;
			return this;
		}

		public Artist build() {
			return Artist.this;
		}

	}
}
