package ChatApp;

import java.util.Iterator;
import java.util.List;

public class SearchMessagesByUser implements Iterator<Message> {

    private final User USER;
    private final List<Message> CHAT_HISTORY;
    private int index = 0;

    public SearchMessagesByUser(User user, List<Message> chatHistory) {
        this.USER = user;
        this.CHAT_HISTORY = chatHistory;
    }

    @Override
    public boolean hasNext() {
        return index < CHAT_HISTORY.size();
    }

    @Override
    public Message next() {
        Message current = CHAT_HISTORY.get(index);
        while(!current.SENDER.equals(USER) && !current.RECIPIENTS.contains(USER)) {
            index++;
        }
        return CHAT_HISTORY.get(index++);
    }
}
