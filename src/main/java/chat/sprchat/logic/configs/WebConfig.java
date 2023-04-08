package chat.sprchat.logic.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.print.attribute.standard.Media;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        configurer
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("html", MediaType.TEXT_HTML)
            .mediaType("js", MediaType.valueOf("application/javascript"));

    }
}
