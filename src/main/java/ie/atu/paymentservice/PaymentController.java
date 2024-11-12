package ie.atu.paymentservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDetails> createPayment(@RequestParam Long bookingId, @RequestParam double amount) {
        PaymentDetails paymentDetails = paymentService.createPayment(bookingId, amount);
        return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentid}")
    public ResponseEntity<PaymentDetails> getPaymentStatus(@PathVariable Long paymentId) {
        return paymentService.getPaymentStatus(paymentId)
                .map(payment -> new ResponseEntity<>(payment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{paymentid}/confirm")
    public ResponseEntity<PaymentDetails> confirmPayment(@PathVariable Long paymentId) {
        PaymentDetails payment = paymentService.confirmPayment(paymentId);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //
        }
    }
}
