package com.example.matheus.scenesexample;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;

public class SimpleSceneExampleActivity extends BaseActivity {

    private Scene sceneOne, sceneTwo, sceneThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_scene_example);
        final ViewGroup content = (ViewGroup) findViewById(R.id.activity_simple_scene_example);
        sceneOne = Scene.getSceneForLayout(content, R.layout.scene_one, this);
        sceneTwo = Scene.getSceneForLayout(content, R.layout.scene_two, this);
        sceneThree = Scene.getSceneForLayout(content, R.layout.scene_three, this);
        sceneOne.enter();
    }

    public void buttonOneClick(View view) {
        TransitionManager.beginDelayedTransition(sceneTwo.getSceneRoot());
        // Trocando Scene sem transition
        sceneTwo.enter();
    }

    public void buttonTwoClick(View view) {
        // Trocando Scene com Transition Default
        TransitionManager.go(sceneThree);
    }

    public void buttonThreeClick(View view) {
        // Trocando Scene com Transition Custom
        final TransitionSet transitionSet = new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new Fade())
                .setOrdering(TransitionSet.ORDERING_TOGETHER);
        TransitionManager.go(sceneOne, transitionSet);
    }
}
