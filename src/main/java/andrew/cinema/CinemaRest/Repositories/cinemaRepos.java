package andrew.cinema.CinemaRest.Repositories;
import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.cinema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface cinemaRepos extends CrudRepository<cinema,Integer> {
    @Query
    cinema findByIdcinema(Integer idcinema);

}
