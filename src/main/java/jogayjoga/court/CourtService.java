package jogayjoga.court;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourtService {

    @Autowired
    private CourtRepository courtRepository;

    public CourtOut create(Court in){
        return CourtParser.to(courtRepository.save(new CourtModel(in)).to());
    }

    // public List<CourtOut> getAllCourts(){
    //     return courtRepository.findAll().stream().map(model -> model.to()).collect(Collectors.toList());
    // }

    public CourtOut get(String id){
        return CourtParser.to(courtRepository.findById(id).map(model -> model.to()).orElse(null));
    }

    // public CourtOut update(String id, UpdateIn in){
    //     return courtRepository.findById(id).map(model -> {
    //         model.update(in);
    //         return courtRepository.save(model).to();
    //     }).orElse(null);
    // }
}
