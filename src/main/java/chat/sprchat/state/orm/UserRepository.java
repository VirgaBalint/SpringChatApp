package chat.sprchat.state.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("select u from User u where u.name = ?1")
    Optional<User> findByName(String name);

}
