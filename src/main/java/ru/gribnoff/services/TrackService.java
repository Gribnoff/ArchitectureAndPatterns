package ru.gribnoff.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gribnoff.persistence.entities.Track;
import ru.gribnoff.persistence.pojo.TrackPojo;
import ru.gribnoff.persistence.repositories.TrackRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrackService {
	private final Map<Long, Track> identityMap = new HashMap<>();

	private final TrackRepository trackRepository;

	public Track findOneById(long id) {
		return identityMap.containsKey(id) ?
				identityMap.get(id) :
				trackRepository.findById(id);
	}

	@Transactional
	public void deleteOneById(long id) {
		identityMap.remove(id);
		trackRepository.deleteById(id);
	}

	@Transactional
	public Track save(TrackPojo pojo) {
		Track track = Track.newBuilder()
				.setTitle(pojo.getTitle())
				.setAlbum(pojo.getAlbum())
				.build();

		identityMap.put(track.getId(), track);
		trackRepository.save(track);
		return track;
	}
}
