package ru.gribnoff.persistence.pojo;

import lombok.Builder;
import lombok.Data;
import ru.gribnoff.persistence.entities.Album;

@Data
@Builder
public class TrackPojo {
	private String title;
	private Album album;
}
