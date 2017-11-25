package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    Button btnStudentSendSms = null;
    Button btnStudentCheckIn = null;

    //BookAdapter bookAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        textDsplyViewstudentName = (TextView)findViewById(R.id.textDisplatSudentName);
        textDsplyViewStudentPhoneNumb = (TextView)findViewById(R.id.textDisplayStudentPhoneNumb);
        listviewStudentCheckout = (ListView)findViewById(R.id.listViewStudentCheckout);

        btnStudentCheckIn = (Button)findViewById(R.id.btnstudentsendSms) ;
        btnStudentSendSms = (Button)findViewById(R.id.btnstudentsendChkIn);

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

    public void btnStudentCheckInBook(View view){
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
    }

    private void clsCheckoutupdateList(int _studentId)
    {
        List<dbCheckoutHelper> _stdcheckoutlist = dbCheckoutHelper.getStudentAllCheckout(_studentId);
        int tablesize = _stdcheckoutlist.size();
        for (int _index = 0; _index < tablesize; _index++) {
            int Bookid = _stdcheckoutlist.get(_index).bookId;
            String _bookName = dbBookHelper.getBookName(Bookid);
            String _duedate = _stdcheckoutlist.get(_index).dueDate;
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
