package ie.atu.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public PaymentDetails createPayment(Long bookingId, double amount) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setBookingId(bookingId);
        paymentDetails.setAmount(amount);
        paymentDetails.setPaymentStatus("pending");
        return paymentRepository.save(paymentDetails);
    }
    public Optional<PaymentDetails> getPaymentStatus(Long bookingId) {
        return paymentRepository.findById(bookingId);
    }

    public PaymentDetails confirmPayment(Long bookingId) {
        Optional<PaymentDetails> paymentDetails = paymentRepository.findById(bookingId);
        if (paymentDetails.isPresent()) {
            PaymentDetails paymentDetail = paymentDetails.get();
            paymentDetail.setPaymentStatus("confirmed");
            return paymentRepository.save(paymentDetail);
        }
        return null;
    }
}
