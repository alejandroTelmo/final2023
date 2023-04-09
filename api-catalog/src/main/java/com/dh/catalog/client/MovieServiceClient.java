package com.dh.catalog.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="api-movie") //,url = "http://localhost:8085/api-movie"
public interface MovieServiceClient {

	@GetMapping("/api/v1/movies/{genre}")
	List<MovieDto> getMovieByGenre(@PathVariable (value = "genre") String genre);
	@GetMapping("/api/v1/movies")
	List<MovieDto> getAll();

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Document(collection = "moviemg")
	class MovieDto{
		@Id
		private Long id;

		private String name;

		private String genre;

		private String urlStream;
	}

}
