package ru.gribnoff.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gribnoff.entities.Track;
import ru.gribnoff.persistnce.pojo.TrackPojo;
import ru.gribnoff.persistnce.repositories.TrackRepository;

@Service
@RequiredArgsConstructor
public class TrackService {
	private final TrackRepository trackRepository;

	public Track findOneById(long id) {
		return trackRepository.findById(id);
	}

	public void deleteOneById(long id) {
		trackRepository.deleteById(id);
	}

	@Transactional
	public Track save(TrackPojo pojo) {
		Track track = Track.newBuilder()
				.setTitle(pojo.getTitle())
				.setAlbum(pojo.getAlbum())
				.build();

		trackRepository.save(track);
		return track;
	}
}
