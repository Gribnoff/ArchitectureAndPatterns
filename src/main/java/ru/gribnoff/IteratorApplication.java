package ru.gribnoff;

import ru.gribnoff.persistence.entities.Album;
import ru.gribnoff.persistence.entities.Iterator;
import ru.gribnoff.persistence.entities.Track;

import java.util.ArrayList;
import java.util.List;

public class IteratorApplication {
	public static void main(String[] args) {
		List<Track> trackList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			trackList.add(Track.newBuilder()
					.setId(i)
					.setTitle("Iterate me, my number is " + i)
					.build());
		}
		Album album = Album.newBuilder()
				.setTitle("Iterables")
				.setTrackList(trackList)
				.build();

		Iterator iterator = album.iterator();
		while (iterator.hasNext()) {
			System.out.println(((Track) iterator.next()).getId());
		}
	}
}
