package andrew.cinema.CinemaRest.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
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

}
