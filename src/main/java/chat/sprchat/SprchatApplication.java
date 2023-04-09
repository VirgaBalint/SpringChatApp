package chat.sprchat;

import chat.sprchat.state.ConnectedClient;
import chat.sprchat.state.LoadedMessage;
import chat.sprchat.state.orm.MsgRepo;
import lombok.Getter;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SprchatApplication
{
	public static final List<LoadedMessage> loadedMessages = new ArrayList<>();
	public static final Set<ConnectedClient> clients = new HashSet<>();

	public static void main(String[] args)
	{
		ConfigurableApplicationContext context = SpringApplication.run(SprchatApplication.class, args);
	}
}
