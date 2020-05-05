package ru.gribnoff.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gribnoff.persistence.entities.Track;
import ru.gribnoff.persistence.pojo.TrackPojo;
import ru.gribnoff.persistence.repositories.TrackRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrackService {
	private final Map<Long, Track> identityMap = new HashMap<>();

	@PersistenceContext
	private EntityManager entityManager;

	private final TrackRepository trackRepository;

	public List<Track> findAll() {
		return trackRepository.findAll();
	}

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

	public List<Track> findAll(String artist, String album, Integer fromYear, Integer toYear) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Track> criteriaQuery = criteriaBuilder.createQuery(Track.class);

		Root<Track> root = criteriaQuery.from(Track.class);
		List<Predicate> predicates = new ArrayList<>();

		if (artist != null) {
			predicates.add(criteriaBuilder.like(root.get("album").get("artist").get("name"), "%" + artist + "%"));
		}

		if (album != null) {
			predicates.add(criteriaBuilder.like(root.get("album").get("title"), "%" + album + "%"));
		}

		if (fromYear != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("album").get("year"), fromYear));
		}

		if (toYear != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("album").get("year"), toYear));
		}

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[]{})));

		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
