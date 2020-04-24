package ru.gribnoff.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class Track implements Issue{

	private long id;
	private String title;
	@JsonBackReference
	private Album album;

	private Track() {
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

		public Track.Builder setId(long id) {
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
