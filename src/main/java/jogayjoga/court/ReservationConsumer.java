package jogayjoga.court;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReservationConsumer {

    @Autowired
    private CourtService courtService;

    @KafkaListener(topics = "reservation-out", groupId = "court")
    public void consume(String id) {
        System.out.println("Consumed reservation: " + id);
        courtService.reserveCourt(id);
    }

}
