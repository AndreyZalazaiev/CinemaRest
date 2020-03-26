package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.hall;
import andrew.cinema.CinemaRest.Entities.session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface hallRepos extends CrudRepository<hall,Integer> {
    @Query
    hall findByIdhall(Integer idhall);
    @Query
    List<hall> findByIdcinema(Integer idcinema);
}
