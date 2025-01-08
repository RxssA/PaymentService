package ie.atu.paymentservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleBookingCreatedEvent(BookingDetailsDTO bookingDetailsDTO) {
        // Handle the received BookingDetailsDTO
        System.out.println("Received BookingDetailsDTO: " + bookingDetailsDTO);
        // Process the booking details as needed
    }
}
