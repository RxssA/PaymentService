package ie.atu.paymentservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Declare the queue
    @Bean
    public Queue bookingQueue() {
        return new Queue("booking.queue", true); // true indicates the queue is durable
    }

    // Declare the exchange
    @Bean
    public DirectExchange bookingExchange() {
        return new DirectExchange("booking.exchange");
    }

    // Bind the queue to the exchange
    @Bean
    public Binding bookingBinding(Queue bookingQueue, DirectExchange bookingExchange) {
        return BindingBuilder.bind(bookingQueue).to(bookingExchange).with("booking.routingKey");
    }
}
