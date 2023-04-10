
package com.dh.catalog.event;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.config.RabbitMQConfigCatalog;
import com.dh.catalog.service.SerieService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SerieCreadaEventConsumer {

    private final SerieService serieService;

    public SerieCreadaEventConsumer(SerieService serieService) {
        this.serieService = serieService;
    }


    @RabbitListener(queues = RabbitMQConfigCatalog.QUEUE_SERIE_CREADA)
    public void listen(SerieServiceClient.SerieDTO mensaje) {
        try{
            System.out.println("New Serie");
            serieService.create(mensaje);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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
