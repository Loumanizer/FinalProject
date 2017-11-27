package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;

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


        Intent intent = getIntent();
        int _studentId = intent.getIntExtra("STUDENT_ID", 0);
        textDsplyViewstudentName.setTag(_studentId);
        textDsplyViewstudentName.setText(dbStudentHelper.getStudentName(_studentId));
        textDsplyViewStudentPhoneNumb.setText(dbStudentHelper.getStudentPhoneNumber(_studentId));

        clsCheckoutupdateList(_studentId);
    }

    public void btnStudentCheckInBook(View view){
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        BookCheckoutAdapter _bookckoutdapter_= (BookCheckoutAdapter) listView.getAdapter();
        int ckid = _bookckoutdapter_.getCheckoutId(position);
        Select query = new Select();
        dbCheckoutHelper choutdb = query.from(dbCheckoutHelper.class)
                .where("id = ?", ckid)
                .executeSingle();
        choutdb.delete();

        clsCheckoutupdateList((int) textDsplyViewstudentName.getTag());
        clsCheckoutclearForm();
    }

    public void btnStudentSendSms(View view){
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        BookCheckoutAdapter _bookckoutdapter = (BookCheckoutAdapter) listView.getAdapter();
        String _studedntPhone = textDsplyViewStudentPhoneNumb.getText().toString();
        String _bookName = _bookckoutdapter.getCheckoutBookName(position);
        String _duedate = _bookckoutdapter.getCheckoutDuedate(position);
        String _smsmsg = "Please return **"  + _bookName + " ** book which is due on : " + _duedate;

        // logic to send SMS

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(_studedntPhone, null, _smsmsg, null, null);
    }

    private void clsCheckoutupdateList(int _studentId)
    {
        ckadapter = new StringAdapter(this);
        bookckoutdapter = new BookCheckoutAdapter();

        listviewStudentCheckout.setAdapter(ckadapter);
        listviewStudentCheckout.setAdapter(bookckoutdapter);

        List<dbCheckoutHelper> _stdcheckoutlist = dbCheckoutHelper.getStudentAllCheckout(_studentId);
        int tablesize = _stdcheckoutlist.size();
        for (int _index = 0; _index < tablesize; _index++) {
            int Bookid = _stdcheckoutlist.get(_index).bookId;
            String _bookName = dbBookHelper.getBookName(Bookid);
            String _duedate = _stdcheckoutlist.get(_index).dueDate;
            int _ckout = _stdcheckoutlist.get(_index).getId().intValue();
            bookckoutdapter.addCheckoutBook(_ckout, _bookName, _duedate);
        }
        listviewStudentCheckout.setAdapter(bookckoutdapter);
    }

    private void clsCheckoutclearForm()
    {
        //editTextDuedate.setTag(-1);
        //editTextDuedate.setText("");
    }
}
