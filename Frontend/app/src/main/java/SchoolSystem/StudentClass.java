package SchoolSystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import SchoolSystem.R;

public class StudentClass extends AppCompatActivity {

    private RequestQueue q;
    private int id;
    private int classId;

    private TextView classTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentclass);

        q = Volley.newRequestQueue(this);

        //Sets the id of the student and the class id
        id = Integer.parseInt(getIntent().getExtras().get("id").toString());
        classId = getIntent().getExtras().getInt("classId");

        //Sets up the text views on the screen
        classTitle = (TextView) findViewById(R.id.studentClassTitle);

        setUp();



    }

    private void setUp() {
        String url = "http://10.0.2.2:8080/student/" + id + "/course/" + classId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Goes into the teacherCourse of the studentCourse
                    JSONObject teacherCourse = response.getJSONObject("teacherCourse");
                    //Gets the individual course of the teacherCourse
                    JSONObject course = teacherCourse.getJSONObject("course");
                    //Gets the name of the course from the course
                    String courseTitle = course.getString("title");

                    classTitle.setText(courseTitle);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        q.add(jsonObjectRequest);

    }
}
