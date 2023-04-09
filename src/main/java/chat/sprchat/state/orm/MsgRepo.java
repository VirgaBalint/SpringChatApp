package chat.sprchat.state.orm;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface MsgRepo extends JpaRepository<Message, Long>
{

}
