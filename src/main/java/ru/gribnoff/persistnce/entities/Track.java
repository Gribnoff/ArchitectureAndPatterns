package ru.gribnoff.persistnce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gribnoff.persistnce.entities.util.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Track extends PersistableEntity {

//	@Id
//	@GeneratedValue
	private UUID id;

	private String title;
//	@ManyToOne
	@JsonBackReference
	private Album album;

	private Track() {
	}

	public static Track.Builder newBuilder() {
		return new Track().new Builder();
	}

	public class Builder {

		private Builder() {
		}

		public Track.Builder setId(UUID id) {
			Track.this.id = id;
			return this;
		}

		public Track.Builder setTitle(String title) {
			Track.this.title = title;
			return this;
		}

		public Track.Builder setAlbum(Album album) {
			Track.this.album = album;
			return this;
		}

		public Track build() {
			return Track.this;
		}

	}
}
