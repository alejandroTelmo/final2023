package com.dh.catalog.controller;
import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.service.MovieService;
import com.dh.catalog.service.SerieService;
import lombok.*;
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

	private  final SerieService serieService;

	private final MovieService movieService;

	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, SerieService serieService, MovieService movieService) {
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
		this.serieService = serieService;
		this.movieService = movieService;
	}
//todas las peliculas por genero a traves de feign
	@GetMapping("movie/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieServiceClient.getMovieByGenre(genre));
	}
	//todas las series a traves de feign x genero
	@GetMapping("/serie/{genre}")
	ResponseEntity<List<SerieServiceClient.SerieDTO>> getSerieByGenre(@PathVariable String genre){
		return ResponseEntity.ok(serieServiceClient.getSerieByGenre(genre));
	}
	//todas las series a traves de feign
	@GetMapping("/serie")
	ResponseEntity<List<SerieServiceClient.SerieDTO>> getSerie(){
		return ResponseEntity.ok(serieServiceClient.getSerie());
	}
	//todas las peliculas y series a traves de feign/online
	@GetMapping("/{genre}")
	ResponseEntity<MovieSerieDTO> getAllMoviesAndSeriesByGenre(@PathVariable String genre){
		List<MovieServiceClient.MovieDto> movieDtos=movieServiceClient.getMovieByGenre(genre);
		List<SerieServiceClient.SerieDTO> serieDTOS=serieServiceClient.getSerieByGenre(genre);
		MovieSerieDTO movieSerieDTO=new MovieSerieDTO(movieDtos,serieDTOS);
		return ResponseEntity.ok(movieSerieDTO);
	}
	//todas las peliculas a traves de feign
	@GetMapping("/movie")
	ResponseEntity<List<MovieServiceClient.MovieDto>>getAllMovies(){
		List<MovieServiceClient.MovieDto> movieDtos= movieServiceClient.getAll();
		return ResponseEntity.ok(movieDtos);
	}
    //metodo de consulta offline, este metodo consulta a la bd mongo de catalog devuelve todas las movies
	@GetMapping("/offlinemovie")
	public List<MovieServiceClient.MovieDto> getMovieOffLine(){
		return movieService.getAll();
	}
	//metodo de consulta offline, este metodo consulta a la bd mongo de catalog y devuelve todas las series
	@GetMapping("/offlineserie")
	public List<SerieServiceClient.SerieDTO> getSerieOffLine(){
		return serieService.getSeries();
	}
	//devuelve todas las movies x genero de catalog mongo
	@GetMapping("/offlinemovie/{genre}")
	public List<MovieServiceClient.MovieDto> getMoviesByGenreOffline(String genre){
		return movieService.findByGenre(genre);
	}
	//devuelve todas las series x genero de catalog mongo
	@GetMapping("/offlineserie/{genre}")
	public List<SerieServiceClient.SerieDTO> getSerieByGenreOffline(String genre){
		return serieService.findByGenre(genre);
	}
	//devuelve todas las peliculas y series x genero de catalog mongo
	@GetMapping("/movieserieoffline/{genre}")
	ResponseEntity<MovieSerieDTO> getAllMoviesAndSeriesByGenreOffline(@PathVariable String genre){

		List<MovieServiceClient.MovieDto> movieDtos=movieService.findByGenre(genre);
		List<SerieServiceClient.SerieDTO> serieDTOS=serieService.findByGenre(genre);
		MovieSerieDTO movieSerieDTO=new MovieSerieDTO(movieDtos,serieDTOS);
		return ResponseEntity.ok(movieSerieDTO);
	}
	//metodo que devuelve todas las series y movies en catalog mongo
	@GetMapping("/movieserie/offline")
	ResponseEntity<MovieSerieDTO> getAllMoviesAndSeriesOffline(){

		List<MovieServiceClient.MovieDto> movieDtos=movieService.getAll();
		List<SerieServiceClient.SerieDTO> serieDTOS=serieService.getSeries();
		MovieSerieDTO movieSerieDTO=new MovieSerieDTO(movieDtos,serieDTOS);
		return ResponseEntity.ok(movieSerieDTO);
	}
	//creo una clase dto que contenga la lista de series y la lista de peliculas
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Builder
	static class MovieSerieDTO{
		List<MovieServiceClient.MovieDto> movies;
		List<SerieServiceClient.SerieDTO> series;
	}
}
