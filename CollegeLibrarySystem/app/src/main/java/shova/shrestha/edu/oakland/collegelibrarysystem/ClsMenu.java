package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClsMenu extends AppCompatActivity {

    Button btnclsMenuStdudent = null;
    Button btnclsMenuBook = null;
    Button btnclsMenuCheckout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls_menu);

        btnclsMenuStdudent = (Button)findViewById(R.id.btnClsMenuStudent);
        btnclsMenuBook = (Button)findViewById(R.id.btnClsMenuBook);
        btnclsMenuCheckout = (Button)findViewById(R.id.btnClsMenuCheckout);

        btnclsMenuStdudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClsMenu.this, ClsStudentList.class);
                startActivity(i);
            }
        });


        btnclsMenuBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClsMenu.this, ClsBookList.class);
                startActivity(i);
            }
        });

        btnclsMenuCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClsMenu.this, ClsCheckoutList.class);
                startActivity(i);
            }
        });

    }
}
