package com.baccar.identify.controller;

import com.baccar.identify.ResourceNotFoundException;
import com.baccar.identify.models.Picture;
import com.baccar.identify.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping("/pictures")
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @GetMapping("/pictures/{id}")
    public ResponseEntity<Picture> getPictureById(@PathVariable(value = "id") Long picId)
            throws ResourceNotFoundException {
        Picture picture = pictureRepository.findById(picId)
                .orElseThrow(() -> new ResourceNotFoundException("Picture not found for this id :: " + picId));
        return ResponseEntity.ok().body(picture);
    }

    @PostMapping("/pictures")
    public Picture createPicture(@Valid @RequestBody Picture picture) {
        picture.setDate_created(new Timestamp(new Date().getTime()) + "");
        return pictureRepository.save(picture);
    }

    @PutMapping("/pictures/{id}")
    public ResponseEntity<Picture> updatePicture(@PathVariable(value = "id") Long picId,
                                                 @Valid @RequestBody Picture pictureDetails) throws ResourceNotFoundException {
        Picture picture = pictureRepository.findById(picId)
                .orElseThrow(() -> new ResourceNotFoundException("Picture not found for this id :: " + picId));

        picture.setName(pictureDetails.getName());
        picture.setFile_location(pictureDetails.getFile_location());
        picture.setThreat(pictureDetails.getThreat());
        picture.setDate_updated(new Timestamp(new Date().getTime()) + "");
        final Picture updatedPicture = pictureRepository.save(picture);
        return ResponseEntity.ok(updatedPicture);
    }

    @DeleteMapping("/pictures/{id}")
    public Map<String, Boolean> deletePicture(@PathVariable(value = "id") Long picId)
            throws ResourceNotFoundException {
        Picture picture = pictureRepository.findById(picId)
                .orElseThrow(() -> new ResourceNotFoundException("Picture not found for this id :: " + picId));

        pictureRepository.delete(picture);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
