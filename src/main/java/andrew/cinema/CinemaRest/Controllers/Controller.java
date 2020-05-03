package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Repositories.filmRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private filmRepos filmRep;
    @RequestMapping("/")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
    @RequestMapping("/about")
    public ModelAndView about () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }
    @GetMapping("/demo")
    public String demoFilms(Map<String,Object> model)
    {
        Iterable<film> flm= filmRep.getALL();
        model.put("films",flm);
        return "films";
    }

}
