package jogayjoga.court;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jogayjoga.sport.SportController;
import jogayjoga.sport.SportOut;
import org.springframework.http.ResponseEntity;

@Service
public class CourtService {

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private SportController sportController;

    public CourtOut create(Court in){
        return CourtParser.to(courtRepository.save(new CourtModel(in)).to());
    }

    // public List<CourtOut> getAllCourts(){
    //     return courtRepository.findAll().stream().map(model -> model.to()).collect(Collectors.toList());
    // }

    public CourtOut get(String id){
        return CourtParser.to(courtRepository.findById(id).map(model -> model.to()).orElse(null));
    }

    @SuppressWarnings("null")
    public CourtSportOut getFullInfo(String id){
        Court court = courtRepository.findById(id).map(model -> model.to()).orElse(null);
        if (null == court) return null;
        String sport_id = null;
        ResponseEntity<SportOut> response = sportController.get(id);
        if (response != null && response.getBody() != null) {
            sport_id = response.getBody().id();
        }
        else{
            sport_id = "";
        }
        return CourtSportOut.builder()
            .id(court.id())
            .name(court.name())
            .address(court.address())
            .sportId(sport_id)
            .build();
    }

    // public CourtOut update(String id, UpdateIn in){
    //     return courtRepository.findById(id).map(model -> {
    //         model.update(in);
    //         return courtRepository.save(model).to();
    //     }).orElse(null);
    // }
}
