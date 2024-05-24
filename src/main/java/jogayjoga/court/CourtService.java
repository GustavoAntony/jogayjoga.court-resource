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
        String sport_name = null;
        ResponseEntity<SportOut> response = sportController.get(court.sportId());
        if (response != null && response.getBody() != null) {
            sport_name = response.getBody().name();
        }
        else{
            sport_name = "";
        }
        return CourtSportOut.builder()
            .id(court.id())
            .name(court.name())
            .address(court.address())
            .sportName(sport_name)
            .build();
    }

    public CourtOut reserveCourt(String id) {
        // Pegar a quadra e atualizar o campo isReserved
        CourtModel courtModel = courtRepository.findById(id).orElse(null);
        
        if (courtModel != null) {
            courtModel.setReserveValue(true);
            courtRepository.save(courtModel);
            return CourtParser.to(courtModel.to());
        } else {
            return null; // Ou tratar de acordo com sua lógica de negócios
        }
    }
    // public CourtOut update(String id, UpdateIn in){
    //     return courtRepository.findById(id).map(model -> {
    //         model.update(in);
    //         return courtRepository.save(model).to();
    //     }).orElse(null);
    // }
}
