package chat.sprchat.state;

import chat.sprchat.state.orm.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @AllArgsConstructor
public class LoadedMessage extends Message
{
    String user;
    String message;
    String date;
}
