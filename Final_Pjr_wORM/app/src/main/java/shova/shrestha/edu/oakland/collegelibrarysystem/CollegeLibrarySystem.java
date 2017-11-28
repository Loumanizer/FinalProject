package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        //Intent i = new Intent(CollegeLibrarySystem.this, ClsStudentList.class);
        //startActivity(i);
        // Temp code to not press login
    }
}
