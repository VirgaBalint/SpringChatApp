package chat.sprchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SprchatApplication 
{
	public static void main(String[] args) 
	{
		ConfigurableApplicationContext context = SpringApplication.run(SprchatApplication.class, args);
	}
}
