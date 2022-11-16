package com.pavlitz.pillar_client.forms;

public enum PillarType {
    AWARENESS("Осознанность","awareness", "Если я сегодня буду действовать на 5% осознаннее...");

    private final String name;
    private final String type;
    private final String phrase;

    PillarType(String name, String type, String phrase) {
        this.name=name;
        this.type=type;
        this.phrase=phrase;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPhrase() {
        return phrase;
    }
}
