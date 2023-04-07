package chat.sprchat.logic;

import chat.sprchat.SprchatApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Mappers
{
    @GetMapping(path = {"/", "", "/home"})
    public String home(Model model)
    {
        return "home";
    }
    @PostMapping("/message")
    public void messageSend()
    {

    }
}
