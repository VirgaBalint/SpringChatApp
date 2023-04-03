package chat.sprchat.logic;

import chat.sprchat.state.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{

}
