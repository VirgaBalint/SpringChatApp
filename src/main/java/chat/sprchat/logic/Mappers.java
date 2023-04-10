package chat.sprchat.logic;

import chat.sprchat.SprchatApplication;
import chat.sprchat.state.LoadedMessage;
import com.mysql.cj.xdevapi.JsonParser;
import lombok.val;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Mappers
{
    @GetMapping("/admin")
    public String admin()
    {
        return "login";
    }
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

    @GetMapping("/getMsg")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity<List<LoadedMessage>> getMsg()
    {
        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(SprchatApplication.loadedMessages, headers, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errorMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
