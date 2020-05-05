package ru.gribnoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gribnoff.persistence.entities.enums.ImageCategory;
import ru.gribnoff.services.ImageService;
import ru.gribnoff.services.TrackService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/music")
@RequiredArgsConstructor
public class MainController {
	private final ImageService imageService;
	private final TrackService trackService;

	@GetMapping(produces = MediaType.TEXT_HTML_VALUE)
	public String getIndexPage(Model model
			,
							   @RequestParam(required = false) String artist,
							   @RequestParam(required = false) String album,
							   @RequestParam(required = false) Integer fromYear,
							   @RequestParam(required = false) Integer toYear
	) {
		model.addAttribute("tracks", trackService.findAll(artist, album, fromYear, toYear));
		return "index";
	}

	@ResponseBody
	@GetMapping(value = "/covers/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public byte[] getAlbumCoverByImageId(@PathVariable String id) throws IOException {
		return getImage(id, ImageCategory.ALBUM_COVER);
	}

	@ResponseBody
	private byte[] getImage(String id, ImageCategory category) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedImage bufferedImage = imageService.loadFileAsResource(id, category);
		if (bufferedImage != null) {
			ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} else {
			return new byte[0];
		}
	}
}
