package ChatApp;

import java.time.ZonedDateTime;
import java.util.Set;

public class Message {

    public final User SENDER;
    public final Set<User> RECIPIENTS;
    public final ZonedDateTime TIME_STAMP;
    public final String CONTENT;

    public Message(User sender, Set<User> recipients, String content) {
        this.SENDER = sender;
        this.RECIPIENTS = recipients;
        TIME_STAMP = ZonedDateTime.now();
        this.CONTENT = content;
    }

    @Override
    public String toString() {
        return SENDER + " has sent the following message at time " + TIME_STAMP + "\n" + CONTENT;
    }
}
