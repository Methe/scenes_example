package com.example.matheus.scenesexample.statemachine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.Scene;
import android.transition.Transition;
import android.view.View;

import java.util.Arrays;

public final class State {

    private View[] visibles;
    private View[] gones;

    @Nullable
    private Transition transition;
    private final Scene scene;

    private State(@NonNull View[] visibles, @NonNull View[] gones) {
        this.visibles = visibles;
        this.gones = gones;
        this.scene = null;
        this.transition = null;
    }

    private State(@NonNull Scene scene, @Nullable Transition transition) {
        this.visibles = new View[0];
        this.gones = new View[0];
        this.scene = scene;
        this.transition = transition;
    }

    View[] getVisibles() {
        return visibles;
    }

    View[] getGones() {
        return gones;
    }

    Scene getScene() {
        return scene;
    }

    @Nullable
    Transition getTransition() {
        return transition;
    }

    private static <T> T[] concat(T[] first, T[] second) {

        if (first == null && second == null) return null;

        if (first == null) return second;

        if (second == null) return first;

        final T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static final class Builder {

        private View[] visibles;
        private View[] gones;
        private Scene scene;
        private Transition transition;

        public Builder visible(View... views) {
            visibles = concat(visibles, views);
            return this;
        }

        public Builder gone(View... views) {
            gones = concat(gones, views);
            return this;
        }

        public Builder scene(@NonNull final Scene scene) {
            return scene(scene, null);
        }

        public Builder scene(@NonNull final Scene scene, @Nullable final Transition transition) {
            this.scene = scene;
            this.transition = transition;
            return this;
        }

        public State build() {
            if (scene == null) return new State(visibles, gones);
            return new State(scene, transition);
        }
    }
}