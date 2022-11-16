package com.pavlitz.pillar_client.services;

import java.util.List;

public interface RestService {
    void postAnswer(String answer, String type);
    boolean checkConnection();
    List<Phrase> getWeeklyByType(String type);
}
