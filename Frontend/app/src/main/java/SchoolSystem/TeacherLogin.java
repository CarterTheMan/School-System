package SchoolSystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import SchoolSystem.R;

public class TeacherLogin extends AppCompatActivity {

    private RequestQueue q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherlogin);
        q = Volley.newRequestQueue(this);

        Button tlbutton = (Button) findViewById(R.id.TLButton);
        TextView tv = (TextView) findViewById(R.id.t_return);

        if (getIntent().hasExtra("Fail")) {
            String text = getIntent().getExtras().getString("Fail");
            tv.setText(text);
        }

        tlbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = (EditText) findViewById(R.id.t_user);
                EditText pass = (EditText) findViewById(R.id.t_pass);
                String username = user.getText().toString();
                String password = pass.getText().toString();
                jsonParse(username, password);
            }
        });

    }

    private void jsonParse(final String u, final String p) {
        String loginURL = "http://10.0.2.2:8080/login-teacher";

        JSONObject object = new JSONObject();
        try {
            object.put("name", u);
            object.put("password", p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, loginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("test", response);
                        String returned = response.toString();
                        String noName = "There are no teachers with the name " + u;
                        if (returned.equals("Incorrect password")) {
                            Intent redo = new Intent(getApplicationContext(), TeacherLogin.class);
                            redo.putExtra("Fail", returned);
                            startActivity(redo);
                        } else if (returned.equals(noName)) {
                            Intent redo = new Intent(getApplicationContext(), TeacherLogin.class);
                            redo.putExtra("Fail", returned);
                            startActivity(redo);
                        } else {
                            Intent cont = new Intent(getApplicationContext(), TeacherHome.class);
                            cont.putExtra("Name", u);                           //Passes the name
                            cont.putExtra("id", Integer.parseInt(returned));    //Passes the id
                            startActivity(cont);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //handle error
                    }
                }) {
            @Override
            public byte[] getBody(){
                JSONObject object = new JSONObject();
                try {
                    object.put("name", u);
                    object.put("password", p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return object.toString().getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };

        q.add(stringRequest);

    }

}
