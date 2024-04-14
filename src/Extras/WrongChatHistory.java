package Extras;

import ChatApp.Message;
import ChatApp.User;

import java.util.*;

/***
 * I am partially leaving this here because it was a lot of work, and honestly it feels more like what a customer would
 * have asked for.  You know when a customer gives you requirements but the requirements are vague, and you don't
 * actually know what they're asking for.  That is sort of what happened here.  Except that I took it to the extreme and
 * made way more than what the customer asked for.  This is my first attempt of ChatHistory, and upon realizing it was
 * wrong, I decided to leave it.  It just won't be used for anything.
 */
public class WrongChatHistory implements Cloneable {

    private Map<Set<User>, List<Message>> history;
    private Message lastMessageSent;


    public WrongChatHistory() {
        history = new HashMap<>();
    }

    public void addMessage(Message message) {
        lastMessageSent = message;
        Set<User> users = getUsers(message);
        history.put(users, getUpdateHistory(message, users));
    }

    public Message lastMessageSent() {
        return lastMessageSent;
    }

    public void viewChat(User currentUser, Set<User> otherUsers) {
        System.out.println("Viewing chat of " + currentUser + " with " + otherUsers);
        otherUsers.add(currentUser);
        List<Message> chat = history.get(otherUsers);
        for(Message message : chat) {
            System.out.println(message);
            System.out.println();
        }
    }

    private Set<User> getUsers(Message message) {
        Set<User> users = new HashSet<>(message.RECIPIENTS);
        users.add(message.SENDER);
        return users;
    }

    private List<Message> getUpdateHistory(Message message, Set<User> users) {
        List<Message> updateHistory;
        if (history.containsKey(users)) {
            updateHistory = history.get(users);
        } else {
            updateHistory = new ArrayList<>();
        }
        updateHistory.add(message);
        return updateHistory;
    }

    @Override
    public WrongChatHistory clone() {
        try {
            WrongChatHistory clone = (WrongChatHistory) super.clone();
            clone.history = new HashMap<>();
            for (Set<User> chats : history.keySet()) {
                List<Message> messages = history.get(chats);
                clone.history.put(new HashSet<>(chats), new ArrayList<>(messages));
            }
            clone.lastMessageSent = lastMessageSent;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
