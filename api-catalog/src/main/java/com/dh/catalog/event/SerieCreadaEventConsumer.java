
package com.dh.catalog.event;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.config.RabbitMQConfigCatalog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SerieCreadaEventConsumer {

    @RabbitListener(queues = RabbitMQConfigCatalog.QUEUE_SERIE_CREADA)
    public void listen(SerieServiceClient.SerieDTO mensaje) {
        System.out.println(mensaje.getName());
        System.out.println(mensaje.getGenre());
        System.out.println("New Serie");
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Serie {
        private String name;
        private String genre;
    }
}
