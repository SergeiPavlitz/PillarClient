package com.pavlitz.pillar_client.forms;

import com.pavlitz.pillar_client.mainView.ViewController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class AnswerForm extends VerticalLayout {
    private TextArea textArea;
    private Button cancel;
    private Button confirm;
    private Label sent;
    private Label task;
    private PillarType type;
    private ViewController viewController;

    private Notification errorNot;
    private Notification successNot;

    private int counter;


    public AnswerForm(ViewController viewController) {

        this.viewController = viewController;
        this.counter = 0;
        task = taskLabel();
        textArea = createArea();
        sent = sendLabel();
        cancel = createCancel();
        confirm = createConfirm();
        HorizontalLayout layout = new HorizontalLayout(sent, cancel,confirm);
        this.add(task, textArea, layout);
        errorNot = createErrorNotification();
        successNot = createSuccessNotification();
    }

    private Notification createSuccessNotification(){
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        Div text = new Div(new Text("Answer submitted"));
        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });
        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);
        notification.add(layout);
        notification.setDuration(3000);
//        notification.setPosition(Notification.Position.MIDDLE);
        return notification;
    }


    private Notification createErrorNotification(){
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Div text = new Div(new Text("Text must be longer then 5 symbols"));
        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });
        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);
        notification.add(layout);
        notification.setDuration(3000);
//        notification.setPosition(Notification.Position.MIDDLE);
        return notification;
    }

    private Button createBtn(String name){
        return new Button(name);
    }

    private Button createCancel(){
        Button b = createBtn("Cancel");
        b.addClickListener(buttonClickEvent -> {
            viewController.hideTextArea();
            textArea.setLabel("empty");
            textArea.setValue("");
            counter = 0;
            sent.setText(String.valueOf(counter));
        });
        return b;
    }

    private Button createConfirm(){
        Button b = createBtn("Confirm");
        b.setDisableOnClick(true);
        b.addClickListener(buttonClickEvent -> {
            if (textArea.getValue().isBlank() || textArea.getValue().length() < 6) {
                errorNot.open();
            }else{
                viewController.confirmAnswer(textArea.getValue(), type.getType());
                successNot.open();
                textArea.setValue("");
                sent.setText(String.valueOf(++counter));
            }
            b.setEnabled(true);
        });
        return b;
    }

    private TextArea createArea(){
        int charLimit = 256;
        TextArea textArea = new TextArea();
        textArea.setWidth(500, Unit.PIXELS);
        textArea.setLabel("empty");
        textArea.setMaxLength(charLimit);
        textArea.setValueChangeMode(ValueChangeMode.EAGER);
        textArea.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });
        return textArea;
    }

    private Label sendLabel(){
        return new Label(String.valueOf(counter));
    }

    private Label taskLabel(){
        return new Label("Напишите 6-10 предолжений фразы.");
    }

    public void setPillarType(PillarType type) {
        this.type = type;
        this.textArea.setLabel(type.getPhrase());
    }


}
