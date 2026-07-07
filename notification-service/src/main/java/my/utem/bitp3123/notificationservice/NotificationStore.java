package my.utem.bitp3123.notificationservice;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class NotificationStore {
    private final List<Notification> notifications = new CopyOnWriteArrayList<>();

    public void addNotification(Notification notification) {
        notifications.add(0, notification); // Keeps newest first
    }

    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notifications);
    }
}