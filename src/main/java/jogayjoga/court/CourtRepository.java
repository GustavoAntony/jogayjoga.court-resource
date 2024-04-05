package jogayjoga.court;
import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends CrudRepository<CourtModel, String>{

    
}
