package ru.gribnoff;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.gribnoff.entities.Album;
import ru.gribnoff.entities.Iterator;
import ru.gribnoff.entities.Track;

import java.util.ArrayList;
import java.util.List;

public class IteratorTest {
	private Album album;

	@Before
	public void setUp() {
		List<Track> trackList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			trackList.add(Track.newBuilder()
					.setId(i)
					.setTitle("Iterate me, my number is " + i)
					.build());
		}
		album = Album.newBuilder()
				.setTitle("Iterables")
				.setTrackList(trackList)
				.build();
	}

	@Test
	public void trackIteratingTest() {
		Iterator iterator = album.iterator();
		int expected = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(expected++, ((Track) iterator.next()).getId());
		}
	}
}
