package chat.sprchat;

import chat.sprchat.state.ConnectedClient;
import chat.sprchat.state.LoadedMessage;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
