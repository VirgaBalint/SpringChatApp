package chat.sprchat.logic;

import chat.sprchat.SprchatApplication;
import chat.sprchat.logic.socket.Server;
import chat.sprchat.state.LoadedMessage;
import chat.sprchat.state.orm.MsgRepo;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Mappers
{
    @PostMapping("/msg-delete/{id}")
    public void deleteMessage(@PathVariable("id") Long id)
    {
        Server.msgRepo.deleteById(id);
    }
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
