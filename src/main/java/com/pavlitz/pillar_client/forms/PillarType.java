package com.pavlitz.pillar_client.forms;

public enum PillarType {
    AWARENESS("awareness", "Если я сегодня буду действовать на 5% осознаннее...");

    private final String type;
    private final String phrase;

    PillarType(String type, String phrase) {
        this.type=type;
        this.phrase=phrase;
    }

    public String getType() {
        return type;
    }

    public String getPhrase() {
        return phrase;
    }
}
