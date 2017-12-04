package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import java.util.List;


public class StudentDetail extends AppCompatActivity {

    final Context context = this;

    StringAdapter ckadapter = null;
    BookCheckoutAdapter bookckoutdapter = null;

    TextView textDsplyViewstudentName  = null;
    TextView textDsplyViewStudentPhoneNumb= null;
    ListView listviewStudentCheckout = null;
    Button btnSudentDetailCheckout = null;

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
        btnSudentDetailCheckout = (Button)findViewById(R.id.btnstudentdetailCheckout);


        Intent intent = getIntent();
        int _studentId = intent.getIntExtra("STUDENT_ID", 0);
        textDsplyViewstudentName.setTag(_studentId);
        textDsplyViewstudentName.setText(dbStudentHelper.getStudentName(_studentId));
        textDsplyViewStudentPhoneNumb.setText(dbStudentHelper.getStudentPhoneNumber(_studentId));

        btnSudentDetailCheckout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_check_out_prompt_by_book_id, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserBookId);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        String _result= userInput.getText().toString();
                                        if(_result.equals(""))
                                        {
                                            Toast.makeText(getApplicationContext(), "Please Enter Book ID", Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            int _maxBookid = dbBookHelper.getMaxBookId();
                                            if(TextUtils.isDigitsOnly(_result))
                                            {
                                                int _ckbookId = Integer.parseInt(_result);
                                                if ((_ckbookId != 0) && (_ckbookId <= _maxBookid))
                                                {
                                                    if(dbBookHelper.getCheckoutBookId(_ckbookId) == 0)
                                                    {
                                                        Toast.makeText(getApplicationContext(), "All book Checkout", Toast.LENGTH_LONG).show();
                                                    }
                                                    else {
                                                        int _studentid = (int) textDsplyViewstudentName.getTag();

                                                        dbCheckoutHelper _checkout = new dbCheckoutHelper(_studentid, _ckbookId, CollegeLibrarySystem.getTodayIssueDate(), CollegeLibrarySystem.getTwoWeekDuedate());
                                                        _checkout.save();
                                                        dbBookHelper.decrementBookbyId(_ckbookId);
                                                        Toast.makeText(getApplicationContext(), dbBookHelper.getCheckoutBookId(_ckbookId) + " Book Remain", Toast.LENGTH_LONG).show();
                                                        clsCheckoutupdateList(_studentid);
                                                        clsCheckoutclearForm();
                                                    }
                                                }
                                                else
                                                {
                                                    Toast.makeText(getApplicationContext(), "Please Enter Correct Book ID", Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            else
                                            {
                                                Toast.makeText(getApplicationContext(), "Please Enter Correct Book ID", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

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
        dbBookHelper.incrementBookbyId(choutdb.bookId);
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
            String _issuedate = _stdcheckoutlist.get(_index).issueDate;
            String _duedate = _stdcheckoutlist.get(_index).dueDate;
            int _ckout = _stdcheckoutlist.get(_index).getId().intValue();
            bookckoutdapter.addCheckoutBook(_ckout, _bookName,_issuedate, _duedate);
        }
        listviewStudentCheckout.setAdapter(bookckoutdapter);
    }

    private void clsCheckoutclearForm()
    {
        //editTextDuedate.setTag(-1);
        //editTextDuedate.setText("");
    }

}
