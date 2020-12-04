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

public class StudentAssignment extends AppCompatActivity {

    private RequestQueue q;
    private int id;
    private int classId;
    private int assignmentId;

    private TextView title;
    private TextView grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentassignment);

        q = Volley.newRequestQueue(this);

        id = getIntent().getExtras().getInt("id");
        classId = getIntent().getExtras().getInt("classId");
        assignmentId = getIntent().getExtras().getInt("assignmentId");

        title = findViewById(R.id.studentAssignmentTitle);
        grade = findViewById(R.id.studentClassDescription);

        setUp();


    }

    private void setUp() {
        String url = "http://10.0.2.2:8080/student/" + id + "/course/" + classId + "/assignment/" + assignmentId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Gets the general course assignment
                    JSONObject couseAssignment = response.getJSONObject("courseAssignment");
                    //Gets the title and grade of the assignment
                    String title = couseAssignment.getString("title");
                    String grade = response.optString("grade", null);

                    TextView titleText = findViewById(R.id.studentAssignmentTitle);
                    TextView gradeText = findViewById(R.id.studentAssignmentGrade);

                    titleText.setText("Title: " + title);
                    if (grade.equals("null")) {
                        gradeText.setText("Grade: N/A");
                    } else {
                        gradeText.setText("Grade: " + grade + "%");
                    }

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
