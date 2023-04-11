package chat.sprchat.state.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;


@Repository
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 6, max = 20)
    @Column(unique = true)
    private String name;

    private String password;

    @Email
    @Column(unique = true)
    private String email;

    private String role;


}
