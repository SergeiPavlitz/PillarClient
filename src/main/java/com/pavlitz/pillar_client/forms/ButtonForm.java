package com.pavlitz.pillar_client.forms;

import com.pavlitz.pillar_client.mainView.ViewController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;


@SpringComponent
@UIScope
public class ButtonForm extends VerticalLayout {
    private final ViewController viewController;

    private Button awareness;
    private Button reportBtn;

    public ButtonForm(ViewController viewController) {
        this.viewController = viewController;
        this.setPadding(true);
        createBtns();

    }

    private void createBtns() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        if (today.equals(DayOfWeek.SATURDAY) || today.equals(DayOfWeek.SUNDAY)) {
            reportBtn = new Button("Недельный отчет");
            reportBtn.addClickListener(buttonClickEvent -> {
                viewController.showReport();
            });
            this.add(reportBtn);
            return;
        }

        awareness = new Button(PillarType.AWARENESS.getName());

//        ComponentEventListener<ClickEvent<Button>> c = new ComponentEventListener<ClickEvent<Button>>() {
//            ButtonForm b = new ButtonForm();
//            @Override
//            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
//                b.add(new Button("sldkfj"));
//            }
//        };
//        b.addClickListener(c);
        awareness.addClickListener(buttonClickEvent -> {
            this.viewController.showTextArea(PillarType.AWARENESS);
        });
        this.add(awareness);



    }

}
