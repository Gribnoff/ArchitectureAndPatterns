package ru.gribnoff;

import ru.gribnoff.persistence.entities.Album;
import ru.gribnoff.persistence.entities.Artist;
import ru.gribnoff.persistence.entities.Track;

public class CompositeApplication {
	public static void main(String[] args) {
		Artist artist = Artist.newBuilder()
				.setName("werkkraft")
				.build();

		Track track = Track.newBuilder().setTitle("techno madness track #1").build();

		Album album = Album.newBuilder()
				.setTitle("radio activity")
				.setArtist(artist)
				.setYear(2222)
				.appendTrack(track)
				.build();

		track.info();
		album.info();
	}
}
