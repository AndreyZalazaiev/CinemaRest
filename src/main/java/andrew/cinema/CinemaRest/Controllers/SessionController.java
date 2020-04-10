package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.*;
import andrew.cinema.CinemaRest.Repositories.cinemaRepos;
import andrew.cinema.CinemaRest.Repositories.filmRepos;
import andrew.cinema.CinemaRest.Repositories.hallRepos;
import andrew.cinema.CinemaRest.Repositories.sessionRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class SessionController {
    @Autowired
    private hallRepos hallRep;
    @Autowired
    private filmRepos filmRep;
    @Autowired
    private sessionRepos sessionRep;

    @GetMapping(path = "/sessions")
    public @ResponseBody
    Iterable<session> getAll() {
        return sessionRep.findAll();
    }

    @RequestMapping("/sessions/add")
    public @ResponseBody
    String addNew(@RequestParam Integer idfilm, @RequestParam Integer idhall, @RequestParam String start,@RequestParam String end) {
        session sn = new session();
        sn.setIdfilm(idfilm);
        sn.setIdhall(idhall);
        sn.setStart(start);
        sn.setEnd(end);
        hall hl=hallRep.findByIdhall(idhall);
        film fl=filmRep.findByIdfilm(idfilm);
        hl.setSession(sn);
        fl.setSession(sn);
        hallRep.save(hl);
        filmRep.save(fl);

        return "Saved";
    }

    @RequestMapping("/sessions/delete")
    public @ResponseBody
    String Remove(@RequestParam int id) {
        sessionRep.deleteById(id);
        return "deleted id:" + id;
    }
    @RequestMapping("/sessions/find")
    public @ResponseBody
    List<session> Sessions(@RequestParam int idfilm) { ;
        return sessionRep.findByIdfilm(idfilm);
    }
}