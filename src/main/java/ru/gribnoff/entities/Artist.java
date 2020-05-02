package ru.gribnoff.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode
public final class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	private Image photo;

	private String description;

	protected Artist() {
	}

	public static Builder newBuilder() {
		return new Artist().new Builder();
	}

	public class Builder {

		private Builder() {
		}

		public Builder setId(long id) {
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
