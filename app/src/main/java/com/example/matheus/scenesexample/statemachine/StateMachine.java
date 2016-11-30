package com.example.matheus.scenesexample.statemachine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Simple finite state machine for view states.
 * This class have the necessary methods to do State transitions
 * Extends this class and implements performChangeState method to make State transitions
 */
public abstract class StateMachine extends HashMap<StateKey, State> {

    private static final String CURRENT_KEY = "StateMachine.CurrentKey";

    private StateKey currentStateKey;
    private OnChangeState onChangeState;

    protected abstract void performChangeState(@NonNull final State state);

    public StateKey getCurrentStateKey() {
        return currentStateKey;
    }

    public StateMachine withOnChangeState(@NonNull final OnChangeState onChangeState) {
        this.onChangeState = onChangeState;
        return this;
    }

    public StateMachine addState(@NonNull final StateKey stateKey, @NonNull final State state) {
        put(stateKey, state);
        return this;
    }

    public void changeState(@NonNull final StateKey stateKey) {
        changeState(stateKey, onChangeState);
    }

    public void changeState(@NonNull final StateKey stateKey,
                            @Nullable final OnChangeState onChangeState) {

        performChangeState(get(stateKey));
        // On change state
        if (onChangeState != null) onChangeState.onStateChanged(stateKey);

        currentStateKey = stateKey;
    }

    public void restoreInstanceState(@NonNull final Bundle savedInstanceState) {
        currentStateKey = (StateKey) savedInstanceState.getSerializable(CURRENT_KEY);
    }

    @NonNull
    public Bundle saveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(CURRENT_KEY, currentStateKey);
        return bundle;
    }

    public interface OnChangeState {
        void onStateChanged(@NonNull final StateKey key);
    }

}