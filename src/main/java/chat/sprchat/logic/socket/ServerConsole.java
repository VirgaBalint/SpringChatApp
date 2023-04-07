package chat.sprchat.logic.socket;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ServerConsole
{
    @Autowired
    Server server;

    @EventListener(ApplicationStartedEvent.class)
    public void console()
    {
        val scn = new Scanner(System.in);
        while(true)
        {
            scn.nextLine();
        }
    }
}
