package com.pavlitz.pillar_client.mainView;

import com.pavlitz.pillar_client.forms.PillarType;
import com.pavlitz.pillar_client.services.Phrase;

import java.util.List;

public interface ViewController {
    void confirmAnswer(String answer, String type);
    void showTextArea(PillarType type);
    void showMainForm();
    void showReport();

    List<Phrase> getPhrasesForWeek(PillarType t);
}
