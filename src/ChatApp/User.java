package ChatApp;

public class User implements IterableByUser {

    private final String NAME; //including just for the sake of identification
    private ChatHistory chatHistory;
    private final MessageMemento MEMENTO = new MessageMemento();



    public User(String name) {
        this.NAME = name;
        chatHistory = new ChatHistory();
    }

    public void sendMessage(Message message) {
        MEMENTO.setHistoryBeforeUndo(chatHistory);
        chatHistory.addMessage(message);
        ChatServer.sendMessage(message);
    }

    public void receiveMessage(Message message) {
        MEMENTO.setHistoryBeforeUndo(chatHistory);
        chatHistory.addMessage(message);
    }

    public void undoLastMessageSent() {
        Message lastMessageSent = chatHistory.lastMessageSent();
        chatHistory = MEMENTO.getHistoryBeforeUndo();
        if (lastMessageSent.SENDER.equals(this)) {
            ChatServer.undoLastMessageSent(lastMessageSent);
        }
    }

    //Once again just for identification
    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public SearchMessagesByUser iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }
}
