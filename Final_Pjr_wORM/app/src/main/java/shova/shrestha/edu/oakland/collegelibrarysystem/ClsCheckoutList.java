package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Update;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    Button btnClsCheckoutupdateDuedate = null;
    EditText editTextDuedate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls_checkout_list);
        btnClsAddCheckout = (Button)findViewById(R.id.btnAddClsCheckout);
        btnClsCheckoutupdateDuedate = (Button)findViewById(R.id.btnAddClsUpdateDuedate);
        btnClsCheckoutupdateDuedate.setEnabled(false);

        editTextDuedate = (EditText) findViewById(R.id.editTextDuedate);
        
        editTextDuedate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                mcurrentDate.add(mcurrentDate.DAY_OF_MONTH, 14);
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(ClsCheckoutList.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                            editTextDuedate.setText(selectedmonth + 1 + "/"+ selectedday+ "/"+ selectedyear);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Due Date");
                mDatePicker.show();  }
        });


        listViewCheckoutList = (ListView)findViewById(R.id.listViewCheckoutlist);


        spinnerStudent = findViewById(R.id.spinnerStudent);
        spinnerBook = findViewById(R.id.spinnerBook);

        students = dbStudentHelper.getAllStudentName();
        studentSpinnerAdapter = new ArrayAdapter<dbStudentHelper>(this, android.R.layout.simple_list_item_1, students);
        spinnerStudent.setAdapter(studentSpinnerAdapter);

        books = dbBookHelper.getAllBookName();
        bookSpinnerAdapter = new ArrayAdapter<dbBookHelper>(this, android.R.layout.simple_list_item_1, books);
        spinnerBook.setAdapter(bookSpinnerAdapter);

        listViewCheckoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView) view).getText().toString();
                String[] split = item.split(": ");
                int _CheckouttId = Integer.parseInt(split[0]);
                //split = split[1].split(", ");
                //split[0].trim();
                //split[1].trim();
                //editTextStdName.setText(split[0]);
                //editTextStdPhoneNumb.setText(split[1]);
                editTextDuedate.setText(dbCheckoutHelper.getDuedate(_CheckouttId));
                editTextDuedate.setTag(_CheckouttId);
                btnClsCheckoutupdateDuedate.setEnabled(true);
            }
        });

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

                if(_duedate.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Due Date", Toast.LENGTH_LONG).show();
                }
                else if (dbBookHelper.getCheckoutBookId(_bookid) == 0)
                {
                    Toast.makeText(getApplicationContext(), "All Book Checkout", Toast.LENGTH_LONG).show();
                }
                else {
                    dbCheckoutHelper _checkout = new dbCheckoutHelper(_studentId, _bookid, CollegeLibrarySystem.getTodayIssueDate(),_duedate);
                    _checkout.save();
                    dbBookHelper.decrementBookbyId(_bookid);
                    Toast.makeText(getApplicationContext(), dbBookHelper.getCheckoutBookId(_bookid) + " Book Remain", Toast.LENGTH_LONG).show();
                    clsCheckoutupdateList();
                    clsCheckoutclearForm();
                }
            }
        });

        btnClsCheckoutupdateDuedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int _checkouttId = (int) editTextDuedate.getTag();
                String _duedate = editTextDuedate.getText().toString();
                int _studentId = dbCheckoutHelper.getStudentId(_checkouttId);
                int _bookId = dbCheckoutHelper.getBookId(_checkouttId);


                // Update record
                new Update(dbCheckoutHelper.class)
                        .set("DueDate = ?",_duedate)
                        .where("id = ?", _checkouttId)
                        .execute();

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
        btnClsCheckoutupdateDuedate.setEnabled(false);
    }
}
