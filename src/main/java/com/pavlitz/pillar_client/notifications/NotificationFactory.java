package com.pavlitz.pillar_client.notifications;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class NotificationFactory {

    public static Notification alert(String message){
        return createNotification(NotificationVariant.LUMO_ERROR, message);
    }

    public static Notification success(String message){
        return createNotification(NotificationVariant.LUMO_SUCCESS, message);
    }

    private static Notification createNotification(NotificationVariant variant, String message) {
        Notification notification = new Notification();
        notification.addThemeVariants(variant);
        Div text = new Div(new Text(message));
        Button closeButton  = notificationCloseBtn();
        closeButton.addClickListener(event -> {
            notification.close();
        });
        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        notification.add(layout);
        notification.setDuration(3000);
        notification.setPosition(Notification.Position.MIDDLE);
        return notification;
    }

    private static Button notificationCloseBtn(){
        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        return closeButton;
    }
}
