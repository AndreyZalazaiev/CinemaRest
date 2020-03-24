package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Entities.session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface filmRepos extends CrudRepository<film,Integer> {

    @Query
    film findByIdfilm(Integer idfilm);
}
