package chat.sprchat.state.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;

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
    private String date;
    private String message;

    public Message(String user, String date, String message)
    {
        this.user = user;
        this.date = date;
        this.message = message;
    }
}
