package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.film;
import org.springframework.data.repository.CrudRepository;

public interface filmRepos extends CrudRepository<film,Integer> {
}
