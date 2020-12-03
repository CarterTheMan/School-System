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
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import SchoolSystem.R;

public class StudentHome extends AppCompatActivity {

    private RequestQueue q;
    private int id = 0;

    //Sets all the class info parts
    private TextView classInfo_1;
    private TextView classInfo_2;
    private TextView classInfo_3;
    private TextView classInfo_4;
    private TextView classInfo_5;
    private TextView classInfo_6;
    private TextView classInfo_7;
    private TextView classInfo_8;

    //Sets the buttons of all the classes
    private Button class_1;
    private Button class_2;
    private Button class_3;
    private Button class_4;
    private Button class_5;
    private Button class_6;
    private Button class_7;
    private Button class_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studenthome);

        q = Volley.newRequestQueue(this);

        //Sets all of the class info parts for the scroll of the page
        classInfo_1 = (TextView) findViewById(R.id.studentClassInfo1);
        classInfo_2 = (TextView) findViewById(R.id.studentClassInfo2);
        classInfo_3 = (TextView) findViewById(R.id.studentClassInfo3);
        classInfo_4 = (TextView) findViewById(R.id.studentClassInfo4);
        classInfo_5 = (TextView) findViewById(R.id.studentClassInfo5);
        classInfo_6 = (TextView) findViewById(R.id.studentClassInfo6);
        classInfo_7 = (TextView) findViewById(R.id.studentClassInfo7);
        classInfo_8 = (TextView) findViewById(R.id.studentClassInfo8);

        //Sets all of the class parts for the scroll of the page
        class_1 = (Button) findViewById(R.id.studentClass1);
        class_2 = (Button) findViewById(R.id.studentClass2);
        class_3 = (Button) findViewById(R.id.studentClass3);
        class_4 = (Button) findViewById(R.id.studentClass4);
        class_5 = (Button) findViewById(R.id.studentClass5);
        class_6 = (Button) findViewById(R.id.studentClass6);
        class_7 = (Button) findViewById(R.id.studentClass7);
        class_8 = (Button) findViewById(R.id.studentClass8);

        //Sets the id of the student
        id = Integer.parseInt(getIntent().getExtras().get("id").toString());

        //Sets up all the buttons so that it shows the classes
        setUp();

        class_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(0);
            }
        });

        class_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(1);
            }
        });

        class_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(2);
            }
        });

        class_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(3);
            }
        });

        class_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(4);
            }
        });

        class_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(5);
            }
        });

        class_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(6);
            }
        });

        class_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClass(7);
            }
        });

    }

    private void setUp() {
        String url = "http://10.0.2.2:8080/student/" + id + "/courses";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //Gets the individual teacherCourse
                        JSONObject teacherCourse = jsonObject.getJSONObject("teacherCourse");
                        //Gets the course from the teacherCourse
                        JSONObject course = teacherCourse.getJSONObject("course");
                        //Gets the name of the course from the course
                        String courseTitle = course.getString("title");

                        //Sets the text view for the class and changes the wording on it to match
                        //the title of the class
                        TextView textViewClass = getTextView(i);
                        textViewClass.setText(courseTitle);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //Makes all the extra buttons and text views disappear
                for (int i = response.length(); i < 8; i++) {
                    TextView temp1 = getTextView(i);
                    Button temp2 = getButton(i);
                    temp1.setVisibility(View.GONE);
                    temp2.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        q.add(jsonArrayRequest);

    }

    private void goToClass(final int classId) {
        String url = "http://10.0.2.2:8080/student/" + id + "/courses";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject jsonObject = response.getJSONObject(classId);
                    //Gets the id from the studentCourse
                    int class_id = jsonObject.getInt("id");

                    //Goes to that individual class
                    Intent cont = new Intent(getApplicationContext(), StudentClass.class);
                    cont.putExtra("id", id);
                    cont.putExtra("classId", class_id);
                    startActivity(cont);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        q.add(jsonArrayRequest);

    }

    private TextView getTextView(int i) {
        if (i == 0) {
            return classInfo_1;
        } else if (i == 1) {
            return classInfo_2;
        } else if (i == 2) {
            return classInfo_3;
        } else if (i == 3) {
            return classInfo_4;
        } else if (i == 4) {
            return classInfo_5;
        } else if (i == 5) {
            return classInfo_6;
        } else if (i == 6) {
            return classInfo_7;
        } else if (i == 7) {
            return classInfo_8;
        } else {
            return null;
        }
    }

    private Button getButton(int i) {
        if (i == 0) {
            return class_1;
        } else if (i == 1) {
            return class_2;
        } else if (i == 2) {
            return class_3;
        } else if (i == 3) {
            return class_4;
        } else if (i == 4) {
            return class_5;
        } else if (i == 5) {
            return class_6;
        } else if (i == 6) {
            return class_7;
        } else if (i == 7) {
            return class_8;
        } else {
            return null;
        }
    }




}

