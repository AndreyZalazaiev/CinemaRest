package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Repositories.filmRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Transactional
public class FilmController {
    //region Film thing
    @Autowired
    private filmRepos filmRep;

    @GetMapping(path = "/films")
    public @ResponseBody
    Iterable<film> getAllFilms() {
        return filmRep.getALL();
    }

    @RequestMapping("/films/add")
    public @ResponseBody
    String addNewFilm(@RequestParam String name
            , @RequestParam String description, @RequestParam String image, @RequestParam String trailer,@RequestParam String ageLimit,@RequestParam String genre) {
        film fm = new film();
        fm.setName(name);
        fm.setDescription(description);
        fm.setImage(image);
        fm.setTrailer(trailer);
        fm.setAgeLimit(ageLimit);
        fm.setGenre(genre);
        filmRep.save(fm);
        return "Saved";
    }
    @RequestMapping("/films/rating")
    public @ResponseBody
    Double Rating(@RequestParam Integer idfilm) {
        if(filmRep.findByIdfilm(idfilm)!=null) {

            return filmRep.Rating(idfilm);
        }
        else return null;
    }

    @RequestMapping("/films/delete")
    public @ResponseBody
    String RemoveFilm(@RequestParam int id) {
        filmRep.deleteById(id);
        return "deleted id:" + id;
    }


}
