package chat.sprchat.state.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_user")
public class User implements UserDetails
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }
    @Override
    public String getPassword()
    {
        return null;
    }
    @Override
    public String getUsername()
    {
        return null;
    }
    @Override
    public boolean isAccountNonExpired()
    {
        return false;
    }
    @Override
    public boolean isAccountNonLocked()
    {
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired()
    {
        return false;
    }
    @Override
    public boolean isEnabled()
    {
        return false;
    }
}
