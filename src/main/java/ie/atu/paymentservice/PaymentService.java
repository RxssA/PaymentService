package ie.atu.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentDetails createPayment(Long bookingId, double amount) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setBookingId(bookingId.toString()); // Convert Long to String if necessary
        paymentDetails.setAmount(amount);
        paymentDetails.setPaymentStatus("pending");
        return paymentRepository.save(paymentDetails);
    }

    public Optional<PaymentDetails> getPaymentStatus(Long paymentId) {
        return paymentRepository.findById(paymentId.toString()); // Ensure `paymentId` is stored as a String
    }

    public PaymentDetails confirmPayment(Long paymentId) {
        Optional<PaymentDetails> optionalPayment = paymentRepository.findById(paymentId.toString());
        if (optionalPayment.isPresent()) {
            PaymentDetails payment = optionalPayment.get();
            payment.setPaymentStatus("confirmed");
            return paymentRepository.save(payment);
        }
        return null; // Return null if payment is not found
    }

}
