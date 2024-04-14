package ChatApp;


public class MessageMemento {
    private ChatHistory historyBeforeUndo;

    public MessageMemento() {
        historyBeforeUndo = new ChatHistory();
    }

    public ChatHistory getHistoryBeforeUndo() {
        return historyBeforeUndo;
    }

    public void setHistoryBeforeUndo(ChatHistory historyBeforeUndo) {
        this.historyBeforeUndo = historyBeforeUndo.clone();
    }
}
