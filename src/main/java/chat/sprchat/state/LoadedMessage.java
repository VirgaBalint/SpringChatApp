package chat.sprchat.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @AllArgsConstructor
public class LoadedMessage
{
    String name;
    String message;
    Date sent;
}
