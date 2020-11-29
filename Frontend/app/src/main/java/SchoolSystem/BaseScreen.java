package SchoolSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

import SchoolSystem.R;

public class BaseScreen extends AppCompatActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basescreen);

        Button sLogin = (Button) findViewById(R.id.StudentLog);
        sLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent goStudent = new Intent(getApplicationContext(), StudentLogin.class);
                startActivity(goStudent);
            }
        });
    }

}