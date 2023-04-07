package chat.sprchat.logic.orm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgRepo extends JpaRepository<Message, Long>
{
}
