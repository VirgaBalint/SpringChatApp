package chat.sprchat.logic;

import chat.sprchat.SprchatApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class Mappers
{
    @PostMapping("/message")
    public void messageSend(Principal user)
    {

    }

    @GetMapping(path = {"/", "", "/home"})
    public String home(Model model)
    {
        return "home";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

}
