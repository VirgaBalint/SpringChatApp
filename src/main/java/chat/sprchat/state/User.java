package chat.sprchat.state;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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
