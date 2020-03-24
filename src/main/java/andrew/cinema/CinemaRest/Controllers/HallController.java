package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.cinema;
import andrew.cinema.CinemaRest.Entities.hall;
import andrew.cinema.CinemaRest.Repositories.cinemaRepos;
import andrew.cinema.CinemaRest.Repositories.hallRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Optional;

@RestController
public class HallController {
    @Autowired
    private hallRepos hallRep;
    @Autowired
    private cinemaRepos cnmRep;

    @GetMapping(path = "/halls")
    public @ResponseBody
    Iterable<hall> getAllHalls() {
        return hallRep.findAll();
    }

    @RequestMapping("/halls/add")
    public @ResponseBody
    String addNewHall(@RequestParam String name,@RequestParam String type, @RequestParam Integer idcinema) {
        cinema cnm = cnmRep.findByIdcinema(idcinema);
        hall hl =new hall();
        hl.setName(name);
        hl.setType(type);
        hl.setIdcinema(idcinema);
        cnm.setHall(hl);

        cnmRep.save(cnm);
        hallRep.save(hl);

        return "Saved";
    }

    @RequestMapping("/halls/delete")
    public @ResponseBody
    String RemoveHall(@RequestParam int id) {
        hallRep.deleteById(id);
        return "deleted id:" + id;
    }
}
