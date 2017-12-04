package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class CollegeLibrarySystem extends AppCompatActivity {
    EditText editTextUserId = null;
    EditText editTextPwd = null;
    Button btnClsLogin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_library_system);

        editTextUserId = (EditText) findViewById(R.id.editTextUserId);


        editTextPwd = (EditText) findViewById(R.id.editTextPwd);

        btnClsLogin = (Button)findViewById(R.id.btnLogIn);


        btnClsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = editTextUserId.getText().toString();
                String password = editTextPwd.getText().toString();
                if(!userName.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Please Enter correct user name", Toast.LENGTH_LONG).show();
                }
                else
                {

                    if (!password.equals("admin"))
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter correct password", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Intent i = new Intent(CollegeLibrarySystem.this, ClsMenu.class);
                        startActivity(i);
                    }
                }
            }
        });

        // Temp code to not press login
//        Intent i = new Intent(CollegeLibrarySystem.this, ClsBookList.class);
//        startActivity(i);
        // Temp code to not press login
    }

    public static String getTwoWeekDuedate()  {
        Calendar cal= Calendar.getInstance();
        cal.add(cal.DAY_OF_MONTH, 14);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return month +  "/" + day +  "/" + year;
    }

    public static String getTodayIssueDate()  {
        Calendar cal= Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return month +  "/" + day +  "/" + year;
    }
}
