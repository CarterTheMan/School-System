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
    private TextView classDescription;
    private TextView classGrade;
    private int totalGradedAssignments;
    private int totalAssignmentPoints;

    //Sets all the assignment info parts
    private TextView info1;
    private TextView info2;
    private TextView info3;
    private TextView info4;
    private TextView info5;
    private TextView info6;
    private TextView info7;
    private TextView info8;
    private TextView info9;
    private TextView info10;

    //Sets all the assignment grade parts
    private TextView grade1;
    private TextView grade2;
    private TextView grade3;
    private TextView grade4;
    private TextView grade5;
    private TextView grade6;
    private TextView grade7;
    private TextView grade8;
    private TextView grade9;
    private TextView grade10;

    //Sets all the buttons for the assignments
    private Button assignment1;
    private Button assignment2;
    private Button assignment3;
    private Button assignment4;
    private Button assignment5;
    private Button assignment6;
    private Button assignment7;
    private Button assignment8;
    private Button assignment9;
    private Button assignment10;


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
        classDescription = (TextView) findViewById(R.id.studentClassDescription);
        classGrade = (TextView) findViewById(R.id.studentClassGrade);

        //Sets all of the assignment info parts
        info1 = (TextView) findViewById(R.id.studentClassAssignmentInfo1);
        info2 = (TextView) findViewById(R.id.studentClassAssignmentInfo2);
        info3 = (TextView) findViewById(R.id.studentClassAssignmentInfo3);
        info4 = (TextView) findViewById(R.id.studentClassAssignmentInfo4);
        info5 = (TextView) findViewById(R.id.studentClassAssignmentInfo5);
        info6 = (TextView) findViewById(R.id.studentClassAssignmentInfo6);
        info7 = (TextView) findViewById(R.id.studentClassAssignmentInfo7);
        info8 = (TextView) findViewById(R.id.studentClassAssignmentInfo8);
        info9 = (TextView) findViewById(R.id.studentClassAssignmentInfo9);
        info10 = (TextView) findViewById(R.id.studentClassAssignmentInfo10);

        //Sets all of the assignment grade parts
        grade1 = (TextView) findViewById(R.id.studentClassAssignmentGrade1);
        grade2 = (TextView) findViewById(R.id.studentClassAssignmentGrade2);
        grade3 = (TextView) findViewById(R.id.studentClassAssignmentGrade3);
        grade4 = (TextView) findViewById(R.id.studentClassAssignmentGrade4);
        grade5 = (TextView) findViewById(R.id.studentClassAssignmentGrade5);
        grade6 = (TextView) findViewById(R.id.studentClassAssignmentGrade6);
        grade7 = (TextView) findViewById(R.id.studentClassAssignmentGrade7);
        grade8 = (TextView) findViewById(R.id.studentClassAssignmentGrade8);
        grade9 = (TextView) findViewById(R.id.studentClassAssignmentGrade9);
        grade10 = (TextView) findViewById(R.id.studentClassAssignmentGrade10);

        //Sets all buttons for the assignments
        assignment1 = (Button) findViewById(R.id.studentClassAssignment1);
        assignment2 = (Button) findViewById(R.id.studentClassAssignment2);
        assignment3 = (Button) findViewById(R.id.studentClassAssignment3);
        assignment4 = (Button) findViewById(R.id.studentClassAssignment4);
        assignment5 = (Button) findViewById(R.id.studentClassAssignment5);
        assignment6 = (Button) findViewById(R.id.studentClassAssignment6);
        assignment7 = (Button) findViewById(R.id.studentClassAssignment7);
        assignment8 = (Button) findViewById(R.id.studentClassAssignment8);
        assignment9 = (Button) findViewById(R.id.studentClassAssignment9);
        assignment10 = (Button) findViewById(R.id.studentClassAssignment10);

        //Initializes the parts that have to do with overall grade
        totalGradedAssignments = 0;
        totalAssignmentPoints = 0;

        setUpStatics();

        setUpAssignments();

        if (totalGradedAssignments == 0) {
            classGrade.setText("Grade: N/A");
        }

        assignment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(0);
            }
        });

        assignment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(1);
            }
        });

        assignment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(2);
            }
        });

        assignment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(3);
            }
        });

        assignment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(4);
            }
        });

        assignment6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(5);
            }
        });

        assignment7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(6);
            }
        });

        assignment8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(7);
            }
        });

        assignment9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(8);
            }
        });

        assignment10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAssignment(9);
            }
        });


    }

    private void setUpStatics() {
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
                    String description = course.getString("description");

                    classTitle.setText("Class: " + courseTitle);
                    classDescription.setText("Class Description: " + description);

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

    private void setUpAssignments() {
        String url = "http://10.0.2.2:8080/student/" + id + "/course/" + classId + "/assignments";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //Gets the courseAssignment of the jsonObject
                        JSONObject courseAssignment = jsonObject.getJSONObject("courseAssignment");
                        //Gets the title of the courseAssignment
                        String title = courseAssignment.getString("title");
                        String grade = jsonObject.optString("grade", null);

                        if (grade.equals("null")) {
                            grade = "N/A";
                        } else {
                            totalGradedAssignments = totalGradedAssignments + 1;
                            totalAssignmentPoints = totalAssignmentPoints + Integer.parseInt(grade);
                            double totalGrade = totalAssignmentPoints/totalGradedAssignments;
                            classGrade.setText("Grade: " + totalGrade + "%");
                            grade = grade + "%";
                        }

                        TextView infoText = getAssignmentInfo(i);
                        infoText.setText(title);

                        TextView gradeText = getAssignmentGrade(i);
                        gradeText.setText(grade);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //Makes all the extra buttons and text views disappear
                for (int i = response.length(); i < 10; i++) {
                    TextView temp1 = getAssignmentInfo(i);
                    TextView temp2 = getAssignmentGrade(i);
                    Button temp3 = getButton(i);
                    temp1.setVisibility(View.GONE);
                    temp2.setVisibility(View.GONE);
                    temp3.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        q.add(jsonArrayRequest);
    }

    private void goToAssignment(int i) {

    }

    private TextView getAssignmentInfo(int i) {
        if (i == 0) {
            return info1;
        } else if (i == 1) {
            return info2;
        } else if (i == 2) {
            return info3;
        } else if (i == 3) {
            return info4;
        } else if (i == 4) {
            return info5;
        } else if (i == 5) {
            return info6;
        } else if (i == 6) {
            return info7;
        } else if (i == 7) {
            return info8;
        } else if (i == 8) {
            return info9;
        } else if (i == 9) {
            return info10;
        } else {
            return null;
        }
    }

    private TextView getAssignmentGrade(int i) {
        if (i == 0) {
            return grade1;
        } else if (i == 1) {
            return grade2;
        } else if (i == 2) {
            return grade3;
        } else if (i == 3) {
            return grade4;
        } else if (i == 4) {
            return grade5;
        } else if (i == 5) {
            return grade6;
        } else if (i == 6) {
            return grade7;
        } else if (i == 7) {
            return grade8;
        } else if (i == 8) {
            return grade9;
        } else if (i == 9) {
            return grade10;
        } else {
            return null;
        }
    }

    private Button getButton(int i) {
        if (i == 0) {
            return assignment1;
        } else if (i == 1) {
            return assignment2;
        } else if (i == 2) {
            return assignment3;
        } else if (i == 3) {
            return assignment4;
        } else if (i == 4) {
            return assignment5;
        } else if (i == 5) {
            return assignment6;
        } else if (i == 6) {
            return assignment7;
        } else if (i == 7) {
            return assignment8;
        } else if (i == 8) {
            return assignment9;
        } else if (i == 9) {
            return assignment10;
        } else {
            return null;
        }
    }

}
