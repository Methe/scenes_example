package com.example.matheus.scenesexample.statemachine;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Implementation of {@link StateMachine}
 * This implementation uses View Visibility to make State transitions
 */
public final class ViewStateMachine extends StateMachine {

    @Override
    protected void performChangeState(@NonNull State state) {
        // Gone
        for (final View view : state.getGones()) view.setVisibility(View.GONE);
        // Visible
        for (final View view : state.getVisibles()) view.setVisibility(View.VISIBLE);
    }

}