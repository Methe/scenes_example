package com.example.matheus.scenesexample;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.matheus.scenesexample.statemachine.SceneStateMachine;
import com.example.matheus.scenesexample.statemachine.State;
import com.example.matheus.scenesexample.statemachine.StateKey;
import com.example.matheus.scenesexample.statemachine.StateMachine;


public class SceneStateMachineActivity extends BaseActivity implements StateMachine.OnChangeState {

    private final SceneStateMachine stateMachine = new SceneStateMachine();
    private ViewGroup content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_state_machine);
        content = (ViewGroup) findViewById(R.id.activity_scene_state_machine);

        final TransitionSet transitionSet = new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new Fade())
                .setOrdering(TransitionSet.ORDERING_TOGETHER);

        stateMachine.withOnChangeState(this)
                .addState(States.SCENE_ONE, getSceneState(R.layout.scene_one, transitionSet))
                .addState(States.SCENE_TWO, getSceneState(R.layout.scene_two, transitionSet))
                .addState(States.SCENE_THREE, getSceneState(R.layout.scene_three, transitionSet));
    }

    @Override
    protected void onResume() {
        super.onResume();
        stateMachine.changeState(States.SCENE_ONE);
    }

    public void buttonOneClick(View view) {
        stateMachine.changeState(States.SCENE_TWO);
    }

    public void buttonTwoClick(View view) {
        stateMachine.changeState(States.SCENE_THREE);
    }

    public void buttonThreeClick(View view) {
        stateMachine.changeState(States.SCENE_ONE);
    }

    @Override
    public void onStateChanged(@NonNull StateKey key) {
        Toast.makeText(this, key.toString(), Toast.LENGTH_SHORT).show();
    }

    public State getSceneState(@LayoutRes final int sceneLayoutRes, @Nullable final Transition transition) {
        return new State.Builder()
                .scene(Scene.getSceneForLayout(content, sceneLayoutRes, this), transition)
                .build();
    }

}
