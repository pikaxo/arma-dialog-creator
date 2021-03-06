package com.kaylerrenslow.armaDialogCreator.gui.notification;

import com.kaylerrenslow.armaDialogCreator.util.ReadOnlyList;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 Handles all {@link Notification} instances that are to be displayed. When a {@link Notification} is shown via {@link #showNotification(Notification)} or {@link #showNotification(Notification, NotificationPane)},
 {@link #getPastNotifications()} will have that notification appended (if {@link Notification#saveToHistory()}) and the notification will be displayed.

 @author Kayler
 @since 11/16/2016 */
public class Notifications {
	private static final Notifications INSTANCE = new Notifications();
	private static final int MAX_HISTORY_NOTIFICATIONS = 15;

	/**
	 Shows the specified {@link Notification}

	 @param notification notification to show
	 @param pane pane to place the notification on
	 */
	public static void showNotification(@NotNull Notification notification, @NotNull NotificationPane pane) {
		INSTANCE.doShowNotification(notification, pane);
	}

	/**
	 Shows the specified {@link Notification} on {@link #getNotificationPane()}

	 @param notification notification to show
	 @throws IllegalStateException when {@link #setDefaultNotificationPane(NotificationPane)} is not invoked prior to this call
	 */
	public static void showNotification(@NotNull Notification notification) {
		if (INSTANCE.notificationPane == null) {
			throw new IllegalStateException("notificationPane is not set from setDefaultNotificationPane()");
		}
		INSTANCE.doShowNotification(notification, INSTANCE.notificationPane);
	}

	/** Return a list of past notifications that were displayed. */
	@NotNull
	public static ReadOnlyList<NotificationDescriptor> getPastNotifications() {
		return INSTANCE.pastNotificationsReadOnly;
	}

	/**
	 Set the {@link NotificationPane} to display the notifications that are invoked from {@link #showNotification(Notification)}. This must be invoked before {@link #showNotification(Notification)}.

	 @param notificationPane the pane
	 */
	public static void setDefaultNotificationPane(@NotNull NotificationPane notificationPane) {
		INSTANCE.notificationPane = notificationPane;
	}

	/** {@link #setDefaultNotificationPane(NotificationPane)} */
	public static NotificationPane getNotificationPane() {
		return INSTANCE.notificationPane;
	}

	private Notifications() {
	}


	private final List<NotificationDescriptor> showingNotifications = Collections.synchronizedList(new LinkedList<>());
	private final NotificationsVisibilityTask visibilityTask = new NotificationsVisibilityTask(this);
	private final List<NotificationDescriptor> pastNotifications = Collections.synchronizedList(new LinkedList<>());
	private final ReadOnlyList<NotificationDescriptor> pastNotificationsReadOnly = new ReadOnlyList<>(pastNotifications);
	private NotificationPane notificationPane;

	private void doShowNotification(@NotNull Notification notification, @NotNull NotificationPane notificationPane) {
		NotificationDescriptor descriptor = new NotificationDescriptor(notification, System.currentTimeMillis());
		if (notification.saveToHistory()) {
			pastNotifications.add(descriptor);
			while (pastNotifications.size() >= MAX_HISTORY_NOTIFICATIONS) {
				pastNotifications.remove(0);
			}
		}
		showingNotifications.add(descriptor);
		notificationPane.addNotification(notification);
		if (!visibilityTask.isRunning()) {
			Thread thread = new Thread(visibilityTask);
			thread.setName("ADC - Notifications Thread");
			thread.setDaemon(true);
			thread.start();
		}
	}

	private static class NotificationsVisibilityTask extends Task<Boolean> {

		private final Notifications notifications;
		private final List<NotificationDescriptor> tohide = Collections.synchronizedList(new LinkedList<>());

		public NotificationsVisibilityTask(@NotNull Notifications notifications) {
			this.notifications = notifications;
		}

		@Override
		protected Boolean call() throws Exception {
			while (true) {
				long now = System.currentTimeMillis();
				for (int i = 0; i < notifications.showingNotifications.size(); ) {
					NotificationDescriptor wrapper = notifications.showingNotifications.get(i);
					if (!wrapper.getNotification().isShowing() || wrapper.getTimeShown() + wrapper.getNotification().getDisplayDurationMilliseconds() <= now) {
						tohide.add(notifications.showingNotifications.remove(i));
						continue;
					}
					i++;
				}
				if (tohide.size() > 0) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							while (tohide.size() > 0) {
								tohide.remove(0).getNotification().setShowing(false);
							}
						}
					});
				}
				if (notifications.showingNotifications.size() == 0) {
					try {
						Thread.sleep(2000);
					} catch (Exception ignore) {
					}
				} else {
					Thread.yield();
				}
			}

		}
	}
}
