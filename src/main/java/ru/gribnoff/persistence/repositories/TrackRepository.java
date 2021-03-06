package ru.gribnoff.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gribnoff.persistence.entities.Track;

import java.util.List;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
	List<Track> findAll();
	List<Track> findAllByAlbum_Title(String album);
	Track findById(long id);
}
