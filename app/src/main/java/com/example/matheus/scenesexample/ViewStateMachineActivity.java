package com.example.matheus.scenesexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.matheus.scenesexample.statemachine.State;
import com.example.matheus.scenesexample.statemachine.StateKey;
import com.example.matheus.scenesexample.statemachine.StateMachine;
import com.example.matheus.scenesexample.statemachine.ViewStateMachine;


public class ViewStateMachineActivity extends BaseActivity implements StateMachine.OnChangeState {

    private final ViewStateMachine stateMachine = new ViewStateMachine();
    private ViewGroup content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_state_machine);
        content = (ViewGroup) findViewById(R.id.activity_view_state_machine);

        // Scene 1
        final View sceneOne = findViewById(R.id.scene_one);
        // Scene 2
        final View labelTwo = findViewById(R.id.label_two);
        final View botaoTwo = findViewById(R.id.botao_two);
        // Scene 3
        final View sceneThree = findViewById(R.id.scene_three);

        stateMachine.withOnChangeState(this)
                .addState(States.SCENE_ONE, new State.Builder()
                        .visible(sceneOne)
                        .gone(labelTwo, botaoTwo, sceneThree).build())
                .addState(States.SCENE_TWO, new State.Builder()
                        .visible(labelTwo, botaoTwo)
                        .gone(sceneOne, sceneThree).build())
                .addState(States.SCENE_THREE, new State.Builder()
                        .visible(sceneThree)
                        .gone(labelTwo, botaoTwo, sceneOne).build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        stateMachine.changeState(States.SCENE_ONE);
    }

    public void buttonOneClick(View view) {
        TransitionManager.beginDelayedTransition(content);
        stateMachine.changeState(States.SCENE_TWO);
    }

    public void buttonTwoClick(View view) {
        TransitionManager.beginDelayedTransition(content);
        stateMachine.changeState(States.SCENE_THREE);
    }

    public void buttonThreeClick(View view) {
        TransitionManager.beginDelayedTransition(content);
        stateMachine.changeState(States.SCENE_ONE);
    }

    @Override
    public void onStateChanged(@NonNull StateKey key) {
        Toast.makeText(this, key.toString(), Toast.LENGTH_SHORT).show();
    }
}
