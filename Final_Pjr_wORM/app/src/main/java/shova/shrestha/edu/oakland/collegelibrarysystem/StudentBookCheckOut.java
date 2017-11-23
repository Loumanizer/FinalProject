package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentBookCheckOut extends AppCompatActivity {

    TextView displayBookitem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_book_check_out);

        displayBookitem = (TextView)findViewById(R.id.textstudentbookcheckout);
    }
}
