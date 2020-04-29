package ru.gribnoff.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
public class Album implements Issue, Iterable{
	private long id;
	private String title;
	private int year;
	@JsonBackReference
	private Artist artist;
	private Image cover;
	private List<String> genres;
	@JsonManagedReference
	private List<Track> trackList;

	private Album() {
		genres = new ArrayList<>();
		trackList = new ArrayList<>();
	}

	public static Album.Builder newBuilder() {
		return new Album().new Builder();
	}

	@Override
	public void info() {
		System.out.printf("This is album \"%s\" by %s containing %d tracks, released in %d\n", title, artist.getName(), trackList.size(), year);
	}

	@Override
	public Iterator iterator() {
		return new TrackIterator();
	}

	public class Builder {

		private Builder() {
		}

		public Builder setId(long id) {
			Album.this.id = id;
			return this;
		}

		public Builder setTitle(String title) {
			Album.this.title = title;
			return this;
		}

		public Builder setYear(int year) {
			Album.this.year = year;
			return this;
		}

		public Builder setArtist(Artist artist) {
			Album.this.artist = artist;
			return this;
		}

		public Builder setCover(Image cover) {
			Album.this.cover = cover;
			return this;
		}

		public Builder appendGenre(String genre) {
			Album.this.genres.add(genre) ;
			return this;
		}

		public Builder setTrackList(List<Track> trackList) {
			for (Track track : trackList)
				track.setAlbum(Album.this);
			Album.this.trackList = trackList;
			return this;
		}

		public Builder appendTrack(Track track) {
			track.setAlbum(Album.this);
			Album.this.trackList.add(track);
			return this;
		}

		public Album build() {
			return Album.this;
		}

	}

	private class TrackIterator implements Iterator{
		private int currentPosition = 0;

		private TrackIterator() {}

		@Override
		public boolean hasNext() {
			return currentPosition < trackList.size();
		}

		@Override
		public Issue next() {
			if (currentPosition >= trackList.size())
				throw new NoSuchElementException();
			return trackList.get(currentPosition++);
		}
	}
}
