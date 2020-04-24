package ru.gribnoff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gribnoff.entities.Album;
import ru.gribnoff.entities.Artist;
import ru.gribnoff.entities.Track;

import java.util.ArrayList;
import java.util.List;

public class BuilderApplication {
	public static void main(String[] args) {
		Artist artist = Artist.newBuilder()
				.setId(1L)
				.setName("bananas")
				.setDescription("prime new banana band")
				.build();

		Album.Builder albumBuilder = Album.newBuilder();
		Album album = albumBuilder
				.setId(1L)
				.setTitle("banana split")
				.setArtist(artist)
				.setYear(2222)
				.appendGenre("trance'n'roll")
				.appendGenre("frenchcore")
				.build();

		List<Track> trackList = new ArrayList<>();
		for (long i = 1; i < 10; i++) {
			Track track = Track.newBuilder()
					.setId(i)
					.setTitle("awesome banana track #" + i)
					.setAlbum(album)
					.build();
			trackList.add(track);
		}

		album = albumBuilder
				.setTrackList(trackList)
				.appendTrack(Track.newBuilder()
						.setId(10L)
						.setAlbum(album)
						.setTitle("last banana track")
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
