package jogayjoga.court;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Court", description = "")
public class CourtResource implements CourtController{
    @Autowired
    private CourtService courtService;

    @Override
    public ResponseEntity<?> create(CourtIn in) {
        Court court = CourtParser.to(in);
        return ResponseEntity.ok(courtService.create(court));
    }

    // @Override
    // public ResponseEntity<List<CourtOut>> getAllCourts() {
    //     return ResponseEntity.ok(courtService.getAllCourts());
    // }

    @Override
    public ResponseEntity<CourtOut> get(String id) {
        return ResponseEntity.ok(courtService.get(id));
    }

    @Override
    public ResponseEntity<CourtSportOut> getFullInfo(String id) {
        return ResponseEntity.ok(courtService.getFullInfo(id));
    }

    @Override
    public ResponseEntity<List<CourtOut>> readall() {
        return ResponseEntity.ok(courtService.readAll());
    }
    

    // @Override
    // public ResponseEntity<CourtOut> update(String id, CourtIn in) {
    //     return ResponseEntity.ok(courtService.update(id, in));
    // }
}
