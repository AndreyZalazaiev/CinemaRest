package andrew.cinema.CinemaRest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping(value = "/hello")
    public String sayHI(@RequestParam(defaultValue = "Andrew") String name) {
        return String.format("Hello %s!", name);
    }
}
