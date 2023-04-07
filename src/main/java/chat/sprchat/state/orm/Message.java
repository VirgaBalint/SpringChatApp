package chat.sprchat.state.orm;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Entity
@Repository
@Table(name = "chat_msgs")
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateSent;
}
