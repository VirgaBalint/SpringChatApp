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
	//public static final List<>
	public static final Set<ConnectedClient> clients = new HashSet<>();
	public static void main(String[] args) 
	{
		ConfigurableApplicationContext context = SpringApplication.run(SprchatApplication.class, args);
	}
}
