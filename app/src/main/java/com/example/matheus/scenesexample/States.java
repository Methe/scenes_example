package com.example.matheus.scenesexample;

import com.example.matheus.scenesexample.statemachine.StateKey;

enum States implements StateKey {

    SCENE_ONE, SCENE_TWO, SCENE_THREE;

    @Override
    public String toString() {
        return name();
    }
}
