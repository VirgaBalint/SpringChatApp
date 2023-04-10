package chat.sprchat.state.orm;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "chat_msgs")
@NoArgsConstructor
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

    public Long getId()
    {
        return id;
    }

    public String getUser()
    {
        return user;
    }

    public Date getDate()
    {
        return date;
    }

    public String getMessage()
    {
        return message;
    }
}
