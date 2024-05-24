package jogayjoga.court;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourtMessagingService {
    
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CourtMessagingService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReservation(String  id) {
        kafkaTemplate.send("reservation-out", id);
    }

}
