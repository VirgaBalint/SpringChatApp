package chat.sprchat.state;

import chat.sprchat.state.orm.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class LoadedMessage
{
    String user;
    String message;
    Date date;
}
