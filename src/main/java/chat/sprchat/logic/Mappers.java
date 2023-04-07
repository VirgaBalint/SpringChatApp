package chat.sprchat.logic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mappers
{
    @GetMapping(path = {"/", "", "/home"})
    public String home(Model model)
    {

        return "home";
    }
}
