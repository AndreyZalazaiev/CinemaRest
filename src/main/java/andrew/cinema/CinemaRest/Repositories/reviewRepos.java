package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.review;
import org.springframework.data.repository.CrudRepository;

public interface reviewRepos extends CrudRepository<review,Integer> {
}
