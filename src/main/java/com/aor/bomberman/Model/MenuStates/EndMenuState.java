package com.aor.bomberman.Model.MenuStates;

import com.aor.bomberman.View.MenuView.EndMenuView;

public class EndMenuState extends MenuState {
    private int messageCode;
    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
        updateObserver(messageCode);
    }

    public EndMenuState(EndMenuView observer) {
        super(observer);
        this.messageCode = 0;
    }


    @Override
    public String processCommand(int commandID) {
        if (commandID == 0 || commandID == 1 ) return "mainScreen";
        return "false";
    }

}
