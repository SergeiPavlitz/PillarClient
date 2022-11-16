package com.pavlitz.pillar_client.forms;

import com.pavlitz.pillar_client.mainView.ViewController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ButtonForm extends VerticalLayout {
    private Button b;
    private ViewController viewController;

    public ButtonForm(ViewController viewController) {
        this.viewController = viewController;
        this.setPadding(true);
        createBtns();

    }

    private void createBtns() {
        b = new Button("Осознанность");

//        ComponentEventListener<ClickEvent<Button>> c = new ComponentEventListener<ClickEvent<Button>>() {
//            ButtonForm b = new ButtonForm();
//            @Override
//            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
//                b.add(new Button("sldkfj"));
//            }
//        };
//        b.addClickListener(c);
        b.addClickListener(buttonClickEvent -> {
            this.viewController.showTextArea(PillarType.AWARENESS);
        });
        this.add(b);
    }

}
