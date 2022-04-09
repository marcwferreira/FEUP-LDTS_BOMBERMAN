package com.aor.bomberman;

import com.aor.bomberman.Model.GameModel;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            GameModel gaming = new GameModel(60, 30);
            gaming.run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}