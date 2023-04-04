package com.shape.ShapeAppSpring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {
	
	private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

	@PostMapping(value = "simple-form-upload-mvc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String, String>> handleFileUploadForm(@RequestPart("file") MultipartFile file)
			throws IOException {

		log.info("handling request parts: {}", file);

		try {

			// chemin de stockages des images
			File f = new File("src/main/resources/");
			final Path path = Paths.get(f.getAbsolutePath() + File.separator + "static" + File.separator + "image");

			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}

			Path filePath = path.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			
			// chemin pour acceder aux images
			String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/static/image/")
					.path(file.getOriginalFilename()).toUriString();

			var result = Map.of("filename", file.getOriginalFilename(), "fileUri", fileUri);
			return ResponseEntity.ok().body(result);

		} catch (IOException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
  
	}


