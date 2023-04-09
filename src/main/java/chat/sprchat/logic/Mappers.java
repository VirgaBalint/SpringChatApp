package chat.sprchat.logic;

import chat.sprchat.SprchatApplication;
import chat.sprchat.state.LoadedMessage;
import com.mysql.cj.xdevapi.JsonParser;
import lombok.val;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class Mappers
{
    @GetMapping(path = {"/", "", "/home"})
    public String home(Model model)
    {
        model.addAttribute("texts", SprchatApplication.loadedMessages);
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

    @GetMapping(path="/getMsg", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMsg()
    {
        return SprchatApplication.loadedMessages.toString();
    }

}
