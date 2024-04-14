import ChatApp.ChatServer;
import ChatApp.Message;
import ChatApp.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChatAppDriver {

    public static void main(String[] args) {
        User bob = new User("Bob");
        User joel = new User("Joel");
        User jill = new User("Jill");
        ChatServer.registerUser(bob);
        ChatServer.registerUser(joel);
        ChatServer.registerUser(jill);
        Set<User> recipients = new HashSet<>();
        recipients.add(joel);
        bob.sendMessage(new Message(bob, recipients, "Hi Joel"));
        recipients.remove(joel);
        recipients.add(bob);
        joel.sendMessage(new Message(joel, recipients, "Hi bb"));
        joel.undoLastMessageSent();
        joel.sendMessage(new Message(joel, recipients, "Hi bob"));
        Iterator<Message> iterator = joel.iterator(bob);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        recipients.remove(bob);
        recipients.add(joel);
        recipients.add(jill);
        bob.sendMessage(new Message(bob, recipients, "Hi jill and joel"));
        //bob.viewChatHistory(recipients);
        recipients.remove(jill);
        recipients.remove(joel);
        recipients.add(bob);
        ChatServer.blockMessages(bob, jill);
        jill.sendMessage(new Message(jill, recipients, "Hi bob"));
    }
}
