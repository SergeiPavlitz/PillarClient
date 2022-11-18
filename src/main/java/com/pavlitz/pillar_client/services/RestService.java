package com.pavlitz.pillar_client.services;

import java.util.List;

public interface RestService {
    boolean postAnswer(String answer, String type);
    boolean checkConnection();
    Phrase[] getWeeklyByType(String type);
}
