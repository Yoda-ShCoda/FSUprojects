package com.danit.healthprofiler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/** The purpose of this class is to offer the functionality of a Start Screen with an intro Logo,
 *  a Start button, and a Quit Button
 */

public class StartActivity extends AppCompatActivity {

    Button btStart;
    Button btQuit;

    /** The constructor assigns references to the btStart and btQuit button variables, as well
     *  as OnClickListeners to each either move to the next Activity or quit the application     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btStart = findViewById(R.id.btStart);
        btQuit = findViewById(R.id.btQuit);

        //((Globals)this.getApplication()).setAge(31);

        btStart.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        btQuit.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        finish();
                    }
                }
        );
    }
}