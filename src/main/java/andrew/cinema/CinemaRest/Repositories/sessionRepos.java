package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.cinema;
import andrew.cinema.CinemaRest.Entities.session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface sessionRepos extends CrudRepository<session,Integer> {
    @Query
    session findByIdsession(Integer idsession);
    @Query
    List<session> findByIdfilm(Integer idfilm);
}
