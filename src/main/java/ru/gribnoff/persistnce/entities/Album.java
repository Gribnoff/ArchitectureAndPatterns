package ru.gribnoff.persistnce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gribnoff.persistnce.entities.util.PersistableEntity;

import javax.persistence.*;
import java.util.List;

@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Album extends PersistableEntity {
	private String title;
	private short year;

//	@OneToOne
	private Artist artist;

//	@OneToOne
	private Image cover;

//	@ElementCollection
//	@CollectionTable(
//			name = "album_genre"//,
//			joinColumns = @JoinColumn(name = "album_id")
//	)
	private List<String> genres;

//	@OneToMany
//	@JoinTable(
//			name = "album_track"//,
//			joinColumns = @JoinColumn(name = "album_id"),
//			inverseJoinColumns = @JoinColumn(name = "album_id")
//	)
//	@JsonManagedReference
	private List<Track> trackList;
}
