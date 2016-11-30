package com.example.matheus.scenesexample.statemachine;

import android.support.annotation.NonNull;
import android.transition.TransitionManager;

/**
 * Implementation of {@link StateMachine}
 * This implementation uses the Scene framework to make State transitions
 */
public final class SceneStateMachine extends StateMachine {

    @Override
    protected void performChangeState(@NonNull State state) {

        if (state.getScene() == null) throw new IllegalStateException("Scene cannot be null");

        if (state.getTransition() == null) state.getScene().enter();
        else TransitionManager.go(state.getScene(), state.getTransition());
    }


}