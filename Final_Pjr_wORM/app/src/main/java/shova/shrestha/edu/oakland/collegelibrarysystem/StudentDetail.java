package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentDetail extends AppCompatActivity {

    StringAdapter ckadapter = null;
    BookCheckoutAdapter bookckoutdapter = null;
	
    TextView textDsplyViewstudentName  = null;
    TextView textDsplyViewStudentPhoneNumb= null;
    ListView listviewStudentCheckout = null;

    //BookAdapter bookAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        textDsplyViewstudentName = (TextView)findViewById(R.id.textDisplatSudentName);
        textDsplyViewStudentPhoneNumb = (TextView)findViewById(R.id.textDisplayStudentPhoneNumb);
        listviewStudentCheckout = (ListView)findViewById(R.id.listViewStudentCheckout);

        ckadapter = new StringAdapter(this);
        bookckoutdapter = new BookCheckoutAdapter();

        listviewStudentCheckout.setAdapter(ckadapter);
        listviewStudentCheckout.setAdapter(bookckoutdapter);

        Intent intent = getIntent();
        int _studentId = intent.getIntExtra("STUDENT_ID", 0);
        textDsplyViewstudentName.setText(dbStudentHelper.getStudentName(_studentId));
        textDsplyViewStudentPhoneNumb.setText(dbStudentHelper.getStudentPhoneNumber(_studentId));

        clsCheckoutupdateList(_studentId);
    }

    private void clsCheckoutupdateList(int _studentId)
    {

        List<dbCheckoutHelper> _checkoutList = dbCheckoutHelper.getStudentAllCheckout(_studentId);
        int _size = _checkoutList.size();
        //int tablesize = dbCheckoutHelper.getCheckoutbookCount();
        for (int _index = 0; _index < _size; _index++) {
                //int Bookid = dbCheckoutHelper.getChecoutBookById(_index, _studentId);
                //String _checkoutstdlistIndex_ = _checkoutList.get(_index).toString();
                int _checkoutstdlistIndex = _checkoutList.get(_index).bookId;
                String _bookName = dbBookHelper.getBookName(_checkoutstdlistIndex);
                //String _duedate = dbCheckoutHelper.getDuedate(_checkoutstdlistIndex);
                String _duedate = _checkoutList.get(_index).dueDate;
                bookckoutdapter.addCheckoutBook(_bookName, _duedate);
        }
        listviewStudentCheckout.setAdapter(bookckoutdapter);


    }

    private void clsCheckoutclearForm()
    {
        //editTextDuedate.setTag(-1);
        //editTextDuedate.setText("");
    }
}
