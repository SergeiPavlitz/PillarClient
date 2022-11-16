package com.pavlitz.pillar_client.mainView;

import com.pavlitz.pillar_client.forms.PillarType;

public interface ViewController {
    void confirmAnswer(String answer, String type);
    void showTextArea(PillarType type);
    void hideTextArea();
}
