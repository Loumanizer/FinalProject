package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClsCheckoutList extends AppCompatActivity {
    Spinner spinnerStudent = null, spinnerBook = null;
    List<dbStudentHelper> students = null;
    List<dbBookHelper> books = null;
    ArrayAdapter<dbStudentHelper> studentSpinnerAdapter = null;
    ArrayAdapter<dbBookHelper> bookSpinnerAdapter = null;
    ListView listViewCheckoutList = null;

    ArrayAdapter<dbCheckoutHelper> checkoutadapter;
    List<dbCheckoutHelper> checkout = new ArrayList<>();

    Button btnClsAddCheckout = null;
    EditText editTextDuedate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls_checkout_list);
        btnClsAddCheckout = (Button)findViewById(R.id.btnAddClsCheckout);
        editTextDuedate = (EditText) findViewById(R.id.editTextDuedate);
        listViewCheckoutList = (ListView)findViewById(R.id.listViewCheckoutlist);


        spinnerStudent = findViewById(R.id.spinnerStudent);
        spinnerBook = findViewById(R.id.spinnerBook);

        students = dbStudentHelper.getAllStudentName();
        studentSpinnerAdapter = new ArrayAdapter<dbStudentHelper>(this, android.R.layout.simple_list_item_1, students);
        spinnerStudent.setAdapter(studentSpinnerAdapter);

        books = dbBookHelper.getAllBook();
        bookSpinnerAdapter = new ArrayAdapter<dbBookHelper>(this, android.R.layout.simple_list_item_1, books);
        spinnerBook.setAdapter(bookSpinnerAdapter);

        btnClsAddCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _duedate = null;

                String item = spinnerStudent.getSelectedItem().toString();
                String[] split = item.split(":");
                int _studentId = Integer.parseInt(split[0]);

                item = spinnerBook.getSelectedItem().toString();
                split = item.split(":");
                int _bookid = Integer.parseInt(split[0]);

                _duedate = editTextDuedate.getText().toString();

                dbCheckoutHelper _checkout = new dbCheckoutHelper(_studentId, _bookid, _duedate);
                _checkout.save();
                clsCheckoutupdateList();
                clsCheckoutclearForm();
            }
        });
        clsCheckoutupdateList();
    }

    private void clsCheckoutupdateList()
    {
        // add it to adapter
        checkout = dbCheckoutHelper.getAllCheckout();
        checkoutadapter = new ArrayAdapter<dbCheckoutHelper>(this, android.R.layout.simple_list_item_1, checkout);
        listViewCheckoutList.setAdapter(checkoutadapter);
    }

    private void clsCheckoutclearForm()
    {
        editTextDuedate.setTag(-1);
        editTextDuedate.setText("");
    }
}
