package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.hall;
import andrew.cinema.CinemaRest.Entities.session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface hallRepos extends CrudRepository<hall,Integer> {
    @Query
    hall findByIdhall(Integer idhall);
}
