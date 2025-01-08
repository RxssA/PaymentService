package ie.atu.paymentservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {

    private final PaymentRepository paymentRepository;

    public PaymentEventListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleBookingCreatedEvent(BookingDetailsDTO bookingDetailsDTO) {
        double price = calculatePrice(bookingDetailsDTO);
        System.out.println("Base Amount: " + bookingDetailsDTO.getAmount());
        System.out.println("Multiplier for Room Type (" + bookingDetailsDTO.getRoomType() + "): " + getMultiplier(bookingDetailsDTO.getRoomType()));
        System.out.println("Number of Guests: " + bookingDetailsDTO.getNumberOfGuests());
        System.out.println("Calculated Price: " + price);

        try {
            // Pretend to save to the database
            System.out.println("Pretending to save PaymentDetails: " + price);
        } catch (Exception e) {
            System.err.println("Error while saving payment: " + e.getMessage());
        }
    }

    private double calculatePrice(BookingDetailsDTO bookingDetailsDTO) {
        double baseAmount = bookingDetailsDTO.getAmount(); // Base amount provided in the payload
        double multiplier = getMultiplier(bookingDetailsDTO.getRoomType());
        return baseAmount * multiplier * bookingDetailsDTO.getNumberOfGuests();
    }

    private double getMultiplier(String roomType) {
        if (roomType == null || roomType.isEmpty()) {
            System.err.println("Room type is null or empty; defaulting to multiplier 1.0");
            return 1.0;
        }
        switch (roomType) {
            case "Single":
                return 1.0;
            case "Double":
                return 1.5;
            case "Suite":
                return 2.0;
            default:
                return 1.0; // Default multiplier
        }
    }
}
