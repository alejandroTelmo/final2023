
package com.dh.catalog.event;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.config.RabbitMQConfigCatalog;
import com.dh.catalog.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MovieCreadaEventConsumer {

    private final MovieService movieService;

    public MovieCreadaEventConsumer(MovieService movieService) {
        this.movieService = movieService;
    }

    @RabbitListener(queues = RabbitMQConfigCatalog.QUEUE_MOVIE_CREADA)
    public void listen(MovieServiceClient.MovieDto mensaje) {
        System.out.println(mensaje.getName());
        System.out.println(mensaje.getGenre());
        System.out.println("New Movie");
        movieService.create(mensaje);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Movie {
        private String name;
        private String genre;
    }
}
