package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.cinema;
import andrew.cinema.CinemaRest.Entities.session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface sessionRepos extends CrudRepository<session,Integer> {
    @Query
    session findByIdsession(Integer idsession);
}
