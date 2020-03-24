package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.cinema;
import andrew.cinema.CinemaRest.Repositories.cinemaRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {
    @Autowired
    private cinemaRepos cinemaRep;

    @GetMapping(path = "/cinema")
    public @ResponseBody
    Iterable<cinema> getAllCinema() {
        return cinemaRep.findAll();
    }

    @RequestMapping("/cinema/add")
    public @ResponseBody
    String addNewCinema(@RequestParam String name, @RequestParam String adress) {
        cinema cm = new cinema();
        cm.setName(name);
        cm.setAdress(adress);
        cinemaRep.save(cm);
        return "Saved";
    }

    @RequestMapping("/cinema/delete")
    public @ResponseBody
    String RemoveCinemaById(@RequestParam int id) {
        cinemaRep.deleteById(id);
        return "deleted id:" + id;
    }
}
