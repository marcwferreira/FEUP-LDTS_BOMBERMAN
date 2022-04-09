package com.aor.bomberman.Model.MenuStates;

import com.aor.bomberman.Model.StateInterface;
import com.aor.bomberman.View.MenuView.DefaultMenuView;

public abstract class MenuState implements StateInterface {
    protected void updateObserver(int updateInfo) { observer.update(updateInfo); }

    protected DefaultMenuView observer;

    public String processCommand(int commandID) { return "";}

    public MenuState(DefaultMenuView observer) { this.observer = observer; }

}
