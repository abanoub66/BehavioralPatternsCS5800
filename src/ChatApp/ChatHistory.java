package ChatApp;

import java.util.LinkedList;
import java.util.List;

public class ChatHistory implements IterableByUser, Cloneable {

    private List<Message> history;
    private Message lastMessageSent;

    public ChatHistory() {
        history = new LinkedList<>();
        lastMessageSent = null;
    }

    public void addMessage(Message message) {
        lastMessageSent = message;
        history.add(message);
    }

    public Message lastMessageSent() {
        return lastMessageSent;
    }

    @Override
    public SearchMessagesByUser iterator(User userToSearchWith) {
        return new SearchMessagesByUser(userToSearchWith, history);
    }

    @Override
    public ChatHistory clone() {
        try {
            ChatHistory clone = (ChatHistory) super.clone();
            clone.history = new LinkedList<>(history);
            clone.lastMessageSent = lastMessageSent;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
