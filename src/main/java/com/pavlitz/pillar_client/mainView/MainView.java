package com.pavlitz.pillar_client.mainView;

import com.pavlitz.pillar_client.forms.AnswerForm;
import com.pavlitz.pillar_client.forms.ButtonForm;
import com.pavlitz.pillar_client.forms.PillarType;
import com.pavlitz.pillar_client.services.RestService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout implements ViewController{

    private final RestService restService;

    private final ButtonForm buttonForm;
    private final AnswerForm answerForm;

    public MainView(@Autowired RestService restService) {
        this.restService = restService;
//        this.setWidth(100, Unit.PERCENTAGE);
//        this.setAlignItems(Alignment.CENTER);
//        setJustifyContentMode(JustifyContentMode.CENTER);
        this.buttonForm = new ButtonForm(this);
        this.answerForm = new AnswerForm(this);
        answerForm.setVisible(false);
        this.add(buttonForm, answerForm);
        this.setMargin(true);
    }


    @Override
    public void confirmAnswer(String answer, String type) {
        restService.postAnswer(answer, type);
    }

    @Override
    public void showTextArea(PillarType type) {
        buttonForm.setVisible(false);
        answerForm.setPillarType(type);
        answerForm.setVisible(true);
    }

    @Override
    public void hideTextArea() {
        buttonForm.setVisible(true);
        answerForm.setVisible(false);
    }
}
