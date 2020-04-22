package ru.gribnoff.persistnce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gribnoff.persistnce.entities.util.PersistableEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class Album{
//	@Id
//	@GeneratedValue
	private UUID id;

	private String title;
	private int year;

//	@OneToOne
	private Artist artist;

//	@OneToOne
	private Image cover;

//	@ElementCollection
//	@CollectionTable(
//			name = "album_genre"//,
//			joinColumns = @JoinColumn(name = "album_id")
//	)
	private List<String> genres;

//	@OneToMany
//	@JoinTable(
//			name = "album_track"//,
//			joinColumns = @JoinColumn(name = "album_id"),
//			inverseJoinColumns = @JoinColumn(name = "album_id")
//	)
	@JsonManagedReference
	private List<Track> trackList;

	private Album() {
		genres = new ArrayList<>();
		trackList = new ArrayList<>();
	}

	public static Album.Builder newBuilder() {
		return new Album().new Builder();
	}

	public class Builder {

		private Builder() {
		}

		public Album.Builder setId(UUID id) {
			Album.this.id = id;
			return this;
		}

		public Album.Builder setTitle(String title) {
			Album.this.title = title;
			return this;
		}

		public Album.Builder setYear(int year) {
			Album.this.year = year;
			return this;
		}

		public Album.Builder setArtist(Artist artist) {
			Album.this.artist = artist;
			return this;
		}

		public Album.Builder setCover(Image cover) {
			Album.this.cover = cover;
			return this;
		}

		public Album.Builder appendGenre(String genre) {
			Album.this.genres.add(genre) ;
			return this;
		}

		public Album.Builder setTrackList(List<Track> trackList) {
			Album.this.trackList = trackList;
			return this;
		}

		public Album.Builder appendTrack(Track track) {
			Album.this.trackList.add(track);
			return this;
		}

		public Album build() {
			return Album.this;
		}

	}
}
