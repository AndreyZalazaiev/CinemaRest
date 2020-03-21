package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.cinema;
import org.springframework.data.repository.CrudRepository;

public interface cinemaRepos extends CrudRepository<cinema,Integer> {
}
