package ru.gribnoff.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.gribnoff.persistence.entities.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
//    @Query(value = "SELECT name FROM public.images " +
//            "WHERE id = :id", nativeQuery = true)
//    String getImageNameByImageId(@Param("id") UUID id);
    Image findImageById(@Param("id") long id);
}