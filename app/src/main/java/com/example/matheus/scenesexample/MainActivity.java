package com.example.matheus.scenesexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewStateMachineClick(View view) {
        startActivity(new Intent(this, ViewStateMachineActivity.class));
    }

    public void sceneStateMachineClick(View view) {
        startActivity(new Intent(this, SceneStateMachineActivity.class));
    }

    public void sceneExampleClick(View view) {
        startActivity(new Intent(this, SimpleSceneExampleActivity.class));
    }
}
