package ie.atu.paymentservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventListener {

    private final PaymentService paymentService;

    public PaymentEventListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleBookingCreatedEvent(BookingDetailsDTO bookingDetails) {
        // Create a payment based on the received booking details
        paymentService.createPayment(Long.valueOf(bookingDetails.getId()), bookingDetails.getAmount());
    }

}
