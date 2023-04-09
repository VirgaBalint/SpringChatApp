package chat.sprchat.state.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "chat_msgs")
@NoArgsConstructor
@Getter
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private Date date;
    private String message;

    public Message(String user, Date date, String message)
    {
        this.user = user;
        this.date = date;
        this.message = message;
    }
}
