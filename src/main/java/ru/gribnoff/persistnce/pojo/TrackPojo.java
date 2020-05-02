package ru.gribnoff.persistnce.pojo;

import lombok.Builder;
import lombok.Data;
import ru.gribnoff.entities.Album;

@Data
@Builder
public class TrackPojo {
	private String title;
	private Album album;
}
