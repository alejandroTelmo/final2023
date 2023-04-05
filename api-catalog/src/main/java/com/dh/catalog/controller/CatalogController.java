package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;

import com.dh.catalog.client.SerieServiceClient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieServiceClient;
	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
	}

	@GetMapping("movie/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieServiceClient.getMovieByGenre(genre));
	}
	@GetMapping("/serie/{genre}")
	ResponseEntity<List<SerieServiceClient.SerieDTO>> getSerieByGenre(@PathVariable String genre){
		return ResponseEntity.ok(serieServiceClient.getSerieByGenre(genre));
	}
	@GetMapping("/serie")
	ResponseEntity<List<SerieServiceClient.SerieDTO>> getSerie(){
		return ResponseEntity.ok(serieServiceClient.getSerie());
	}
	@GetMapping("/{genre}")
	ResponseEntity<MovieSerieDTO> getAllMoviesAndSeriesByGenre(@PathVariable String genre){

		List<MovieServiceClient.MovieDto> movieDtos=movieServiceClient.getMovieByGenre(genre);
		List<SerieServiceClient.SerieDTO> serieDTOS=serieServiceClient.getSerieByGenre(genre);
		MovieSerieDTO movieSerieDTO=new MovieSerieDTO(movieDtos,serieDTOS);
		return ResponseEntity.ok(movieSerieDTO);
	}

	@Getter
	@Setter
	@Builder
	static class MovieSerieDTO{
		List<MovieServiceClient.MovieDto> movies;
		List<SerieServiceClient.SerieDTO> series;
	}
}
