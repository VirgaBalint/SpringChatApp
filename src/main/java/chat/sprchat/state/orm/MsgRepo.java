package chat.sprchat.state.orm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgRepo extends JpaRepository<Message, Long>
{
}
