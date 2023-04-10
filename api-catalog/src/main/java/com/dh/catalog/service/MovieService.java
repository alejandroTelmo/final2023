package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.repository.MovieRepository;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieServiceClient movieServiceClient;

    public MovieService(MovieRepository movieRepository, MovieServiceClient movieServiceClient) {
        this.movieRepository = movieRepository;
        this.movieServiceClient = movieServiceClient;
    }

    public String create(MovieServiceClient.MovieDto movieDto){
        movieRepository.save(movieDto);
        return movieDto.getId();
    }
    public List<MovieServiceClient.MovieDto> getAll(){
        return movieRepository.findAll();
    }

    public List<MovieServiceClient.MovieDto> findByGenre(String genre){
        return  movieRepository.findByGenre(genre);
    }
//este metodo esta configurado para que en caso de fallar se hagan reintentos para ver si se recupera,si supera la tasa de fallos
    // se llama al plan B , el metodo que da una solucion prevista, si falla el online, se llama al
    // metodo que devuelve todas las peliculas offline representados x el metodo getAll() que devuelve
    //las peliculas guardadas en en la propia BD de catalog
   /** @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "catalog",fallbackMethod = "getAll")
    public List<MovieServiceClient.MovieDto> getMoviesOnline(){
        return movieServiceClient.getAll();
    }*/
}
