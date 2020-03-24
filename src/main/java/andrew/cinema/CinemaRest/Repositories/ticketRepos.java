package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.ticket;
import org.springframework.data.repository.CrudRepository;

public interface ticketRepos extends CrudRepository<ticket,Integer> {
}
