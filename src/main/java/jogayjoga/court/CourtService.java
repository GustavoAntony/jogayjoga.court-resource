package jogayjoga.court;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(key = "#id", value = "court", unless = "#result == null")
    public CourtOut get(String id){
        System.out.println("Reading court id: " + id);
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

    public List<CourtOut> readAll() {
        List<CourtModel> courtModels = new ArrayList<>();
        courtRepository.findAll().forEach(courtModels::add);

        List<Court> courts = courtModels.stream()
            .map(CourtModel::to)
            .collect(Collectors.toList());

        if (courts.isEmpty()) {
            return null;
        }

        // converte courts para List<CourtOut>
        List<CourtOut> CourtOuts = new ArrayList<>();
        for (Court court : courts) {
            CourtOuts.add(CourtParser.to(court));
        }

        return CourtOuts;
    }

    public CourtOut reserveCourt(String id) {
        // Pegar a quadra e atualizar o campo isReserved
        CourtModel courtModel = courtRepository.findById(id).orElse(null);
        
        if (courtModel != null) {
            courtModel.setReserveValue(1);
            courtRepository.save(courtModel);
            return CourtParser.to(courtModel.to());
        } else {
            return null; // Ou tratar de acordo com sua lógica de negócios
        }
    }

    public void delete(String id){
        courtRepository.deleteById(id);
    }

    public CourtOut update(String id, CourtIn in){
        Court court = courtRepository.findById(id).map(CourtModel::to).orElse(null);
        if (court == null) {
            // Handle the case when the court is not found
            throw new RuntimeException("Court not found");
        }
        court.name(in.name()); // Fluent setter
        court.address(in.address());
        court.sportId(in.sportId());
        CourtModel updatedModel = courtRepository.save(new CourtModel(court));

        // Convert the updated Court object to a CourtOut object and return it
        return CourtParser.to(updatedModel.to());
    }

    // public CourtOut update(String id, UpdateIn in){
    //     return courtRepository.findById(id).map(model -> {
    //         model.update(in);
    //         return courtRepository.save(model).to();
    //     }).orElse(null);
    // }
}
