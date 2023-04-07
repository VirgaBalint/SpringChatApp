package chat.sprchat;

import chat.sprchat.state.ConnectedClient;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SprchatApplication 
{

	private static final Set<ConnectedClient> clients = new HashSet<>();
	public static void addConnectedClient(ConnectedClient client)
	{
		clients.add(client);
	}
	public static void removeConnectedClient(ConnectedClient client)
	{
		clients.remove(client);
	}
	public static Set<ConnectedClient> getClients()
	{
		return clients;
	}

	public static void main(String[] args) 
	{
		ConfigurableApplicationContext context = SpringApplication.run(SprchatApplication.class, args);
	}
}
