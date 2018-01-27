package xyz.digicard.app.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        View register_button = findViewById(R.id.reg_btn);
        register_button.setOnClickListener(this);
    }

    public void onClick(View v){
        openNewActivity(v);
    }
    public void openNewActivity(View v){
        if(v.getId() == R.id.reg_btn){
            Button btn = (Button)findViewById(R.id.reg_btn);
            btn.setText("Debugging");
            Intent intent  = new Intent(MainActivity2.this, NewActivity.class);
            startActivity(intent);
        }
    }

}
