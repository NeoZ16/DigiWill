package de.digiwill.model;

public enum ActionType {

    EMAIL(0),
    WEBHOOK(1);

    private int type;

    ActionType(int type) {
        this.type = type;
    }

    public int getActionType() {
        return type;
    }

}
