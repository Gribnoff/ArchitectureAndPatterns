package ru.gribnoff.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import ru.gribnoff.persistence.entities.Image;
import ru.gribnoff.persistence.entities.enums.ImageCategory;
import ru.gribnoff.persistence.repositories.ImageRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${files.storepath.artist-photos}")
    private Path ARTIST_IMAGES_PATH;

    @Value("${files.storepath.icons}")
    private Path ICONS_PATH;

    @Value("${files.storepath.album-covers}")
    private Path ALBUM_COVERS_PATH;

    @Value("${files.storepath.images-dump}")
    private Path IMAGES_DUMP;

    private final ImageRepository imageRepository;

    private Image getImageById(long id) {
        return imageRepository.findImageById(id);
    }

    public BufferedImage loadFileAsResource(String id, ImageCategory category) throws IOException {
        Resource resource = null;
        switch (category) {
            case ALBUM_COVER:
                resource = new UrlResource(ALBUM_COVERS_PATH
                        .resolve(getImageById(Long.parseLong(id)).getName())
                        .normalize()
                        .toUri());
                break;
            case ARTIST_PHOTO:
                resource = new UrlResource(ARTIST_IMAGES_PATH
                        .resolve(getImageById(Long.parseLong(id)).getName())
                        .normalize()
                        .toUri());
                break;
            case ICON:
                resource = new UrlResource(ICONS_PATH
                        .resolve(id)
                        .normalize()
                        .toUri());
                break;
        }

        return resource.exists() ?
                ImageIO.read(resource.getFile()) :
                ImageIO.read(new UrlResource(ICONS_PATH
                        .resolve("image_not_found.png")
                        .normalize()
                        .toUri())
                        .getFile());
    }
}
