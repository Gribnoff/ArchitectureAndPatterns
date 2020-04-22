package ru.gribnoff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gribnoff.persistnce.entities.Album;
import ru.gribnoff.persistnce.entities.Artist;
import ru.gribnoff.persistnce.entities.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuilderApplication {
	public static void main(String[] args) {
		Artist artist = Artist.newBuilder()
				.setId(UUID.fromString("90806402-753e-436b-85c4-cbe84e4644eb"))
				.setName("bananas")
				.setDescription("prime new banana band")
				.build();

		Album.Builder albumBuilder = Album.newBuilder();
		Album album = albumBuilder
				.setId(UUID.fromString("90806402-753e-436b-85c4-cbe84e4644eb"))
				.setTitle("banana split")
				.setArtist(artist)
				.setYear(2222)
				.appendGenre("trance'n'roll")
				.appendGenre("frenchcore")
				.build();

		List<Track> trackList = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Track track = Track.newBuilder()
					.setId(UUID.fromString("90806402-753e-436b-85c4-cbe84e4644eb".replace('9', (char) (i + '0'))))
					.setTitle("awesome banana track #" + i)
					.setAlbum(album)
					.build();
			trackList.add(track);
		}

		album = albumBuilder
				.setTrackList(trackList)
				.appendTrack(Track.newBuilder()
						.setId(UUID.fromString("00806402-753e-436b-85c4-cbe84e4644eb"))
						.setAlbum(album)
						.setTitle("last track")
						.build())
				.build();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println("Artist:");
			System.out.println(objectMapper.writeValueAsString(artist));
			System.out.println("\nAlbum:");
			System.out.println(objectMapper.writeValueAsString(album));
			System.out.println("\nTracks:");
			for (Track track : album.getTrackList()) {
				System.out.println(objectMapper.writeValueAsString(track));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
