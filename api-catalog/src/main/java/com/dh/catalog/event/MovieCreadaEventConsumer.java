
package com.dh.catalog.event;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.config.RabbitMQConfigCatalog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MovieCreadaEventConsumer {

    @RabbitListener(queues = RabbitMQConfigCatalog.QUEUE_MOVIE_CREADA)
    public void listen(MovieServiceClient.MovieDto mensaje) {
        System.out.println(mensaje.getName());
        System.out.println(mensaje.getGenre());
        System.out.println("New Movie");
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
