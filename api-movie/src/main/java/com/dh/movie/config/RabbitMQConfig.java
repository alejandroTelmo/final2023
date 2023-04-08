package com.dh.movie.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "backendExchange";
    public static final String TOPIC_MOVIE_CREADA = "com.dh.backend.moviecreada";

    public static  final String QUEUE_MOVIE_CREADA="queueMovieCreada";


    @Bean
    public Queue queueMovieCreada(){
        return new Queue(QUEUE_MOVIE_CREADA);
    }

    @Bean
    public TopicExchange appExchangeMovie(){
        return new TopicExchange(EXCHANGE_NAME);

    }

    @Bean
    public Binding bindingMovieCreada(){
        return BindingBuilder.bind(queueMovieCreada()).to(appExchangeMovie()).with(TOPIC_MOVIE_CREADA);
    }
    @Bean
    public RabbitTemplate rabbitTemplateMovie(ConnectionFactory connectionFactory){
        final  var rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverterMovie());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverterMovie(){
        return new Jackson2JsonMessageConverter();
    }
}
