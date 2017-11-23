package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CollegeLibrarySystem extends AppCompatActivity {

    Button btnClsLogin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_library_system);

        btnClsLogin = (Button)findViewById(R.id.btnLogIn);

        btnClsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CollegeLibrarySystem.this, ClsMenu.class);
                startActivity(i);
            }
        });

        // Temp code to not press login
        Intent i = new Intent(CollegeLibrarySystem.this, ClsCheckoutList.class);
        startActivity(i);
        // Temp code to not press login
    }
}
