package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface accountRepos extends CrudRepository<account,Integer> {
    @Query("select u from account u where u.Name = ?1 ")
    account findByName(String Name);
}
