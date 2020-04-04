package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.*;
import andrew.cinema.CinemaRest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return reviewRep.findAll();
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
    List<review> find(@RequestParam int idfilm) {
        return reviewRep.findByIdfilm(idfilm);
    }
    @RequestMapping("/reviews/delete")
    public @ResponseBody
    String Remove(@RequestParam int id) {
        reviewRep.deleteById(id);
        return "deleted id:" + id;
    }

}