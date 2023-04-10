package chat.sprchat.logic.configs;

import chat.sprchat.logic.socket.Server;
import chat.sprchat.state.orm.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        val userTMP = Server.userRepo.findByName(name);
        if (userTMP.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        val user = userTMP.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getRole().equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                authorities);
    }
}
