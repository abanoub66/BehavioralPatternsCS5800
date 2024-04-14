package ChatApp;

import java.util.*;

public class ChatServer {

    private static final List<User> REGISTERED = new ArrayList<>();
    private static final Map<User, Set<User>> BLOCKING_MAP = new HashMap<>();


    public static void registerUser(User user) {
        REGISTERED.add(user);
        BLOCKING_MAP.put(user, new HashSet<>());
    }

    public static void unregisterUser(User user) {
        REGISTERED.remove(user);
        BLOCKING_MAP.remove(user);
    }

    public static void sendMessage(Message message) {
        sendOrUndo(message, 'S');
    }

    public static void undoLastMessageSent(Message message) {
        sendOrUndo(message, 'U');
    }

    public static void blockMessages(User blocker, User blocked) {
        Set<User> updateBlockedSet = BLOCKING_MAP.get(blocker);
        updateBlockedSet.add(blocked);
        BLOCKING_MAP.put(blocker, updateBlockedSet);
    }

    private static void sendOrUndo(Message message, char command) {
        Set<User> recipients = message.RECIPIENTS;
        User sender = message.SENDER;
        if (!isRegistered(sender)) {
            System.out.println("unregistered user"); // probably throw an exception here
            // but I'm lazy and you didn't say to
            return;
        }
        for (User recipient : recipients) {
            if (isRegistered(recipient) && !isBlocked(sender, recipient)) {
                if (command == 'S') {
                    recipient.receiveMessage(message);
                }
                if (command == 'U') {
                    recipient.undoLastMessageSent();
                }
            }
            if (isBlocked(sender, recipient)) {
                System.out.println("Oh no! You're blocked!");
            }
        }
    }

    private static boolean isBlocked(User sender, User recipient) {
        Set<User> blockedSet = BLOCKING_MAP.get(recipient);
        return blockedSet.contains(sender);
    }

    private static boolean isRegistered(User user) {
        return REGISTERED.contains(user);
    }
}
