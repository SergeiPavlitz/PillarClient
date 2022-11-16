package com.pavlitz.pillar_client.mainView;

import com.pavlitz.pillar_client.forms.AnswerForm;
import com.pavlitz.pillar_client.forms.ButtonForm;
import com.pavlitz.pillar_client.forms.PillarType;
import com.pavlitz.pillar_client.forms.ReportForm;
import com.pavlitz.pillar_client.notifications.NotificationFactory;
import com.pavlitz.pillar_client.services.Phrase;
import com.pavlitz.pillar_client.services.RestService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route
public class MainView extends VerticalLayout implements ViewController {

    private final RestService restService;

    private final ButtonForm buttonForm;
    private final AnswerForm answerForm;
    private final ReportForm reportForm;

    public MainView(@Autowired RestService restService) {
        this.restService = restService;
//        this.setWidth(100, Unit.PERCENTAGE);
//        this.setAlignItems(Alignment.CENTER);
//        setJustifyContentMode(JustifyContentMode.CENTER);
        this.buttonForm = new ButtonForm(this);
        this.answerForm = new AnswerForm(this);
        this.reportForm = new ReportForm(this);
        answerForm.setVisible(false);
        reportForm.setVisible(false);
        this.add(buttonForm, answerForm, reportForm);
        this.setMargin(true);
        if (!checkConnection()) {
            buttonForm.setVisible(false);
            Notification connectionAlert = NotificationFactory.alert("Connection refused");
            connectionAlert.setDuration(5000);
            connectionAlert.open();
        }
    }

    private boolean checkConnection() {
        return restService.checkConnection();
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
    public void showMainForm() {
        buttonForm.setVisible(true);
        answerForm.setVisible(false);
        reportForm.setVisible(false);
    }

    @Override
    public void showReport() {
        buttonForm.setVisible(false);
        answerForm.setVisible(false);
        reportForm.setVisible(true);
    }

    @Override
    public List<Phrase> getPhrasesForWeek(PillarType t) {
        return restService.getWeeklyByType(t.getType());
    }
}
