package ru.gribnoff.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode
public final class Track implements Issue{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	@ManyToOne
	@JoinTable(
			name = "album_track",
			joinColumns = @JoinColumn(name = "track_id"),
			inverseJoinColumns = @JoinColumn(name = "album_id")
	)
	@JsonBackReference
	private Album album;

	protected Track() {
	}

	public static Track.Builder newBuilder() {
		return new Track().new Builder();
	}

	@Override
	public void info() {
		System.out.printf("A track called \"%s\" from \"%s\" album of %s\n", title, album.getTitle(), album.getArtist().getName());
	}

	public class Builder {

		private Builder() {
		}

		public Builder setId(long id) {
			Track.this.id = id;
			return this;
		}

		public Builder setTitle(String title) {
			Track.this.title = title;
			return this;
		}

		public Builder setAlbum(Album album) {
			Track.this.album = album;
			return this;
		}

		public Track build() {
			return Track.this;
		}

	}
}
