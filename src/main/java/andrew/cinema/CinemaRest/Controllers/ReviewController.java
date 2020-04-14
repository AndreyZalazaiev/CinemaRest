package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.*;
import andrew.cinema.CinemaRest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RestController
public class ReviewController {
    @Autowired
    private accountRepos accRep;
    @Autowired
    private filmRepos filmRep;
    @Autowired
    private reviewRepos reviewRep;

    @GetMapping(path = "/reviews")
    public @ResponseBody
    Iterable<review> getAll() {
        return reviewRep.getEveryone();
    }

    @GetMapping(path = "/reviews/update")
    public @ResponseBody
    String Update(@RequestParam Integer idreview, @RequestParam String text, @RequestParam Integer mark) {
        Optional<review> check = reviewRep.findById(idreview);
        if(!check.equals(null)) {
            reviewRep.setValue(idreview, text, mark);
            return "updated";
        }
        return "idreview not found or something";
    }
    @RequestMapping("/reviews/add")
    public @ResponseBody
    String addNew(@RequestParam Integer idfilm, @RequestParam String idaccount, @RequestParam String text,@RequestParam Integer mark) {
        review rev=new review();
        rev.setIdfilm(idfilm);
        rev.setIdaccount(idaccount);
        rev.setText(text);
        rev.setMark(mark);
        account acc=accRep.findByIdaccount(idaccount);
        film flm =filmRep.findByIdfilm(idfilm);
        acc.setReview(rev);
        flm.setReview(rev);
        accRep.save(acc);
        filmRep.save(flm);

        return "Saved";
    }
    @RequestMapping("/reviews/find")
    public @ResponseBody
    Iterable<review> find(@RequestParam int idfilm) {
        return reviewRep.getDistinctByIdfilm(idfilm);
    }
    @RequestMapping("/reviews/delete")
    public @ResponseBody
    String Remove(@RequestParam int id) {
        reviewRep.deleteById(id);
        return "deleted id:" + id;
    }

}