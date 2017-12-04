package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Update;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ClsBookList extends AppCompatActivity {

    EditText editTextBookName = null;
    EditText editTextTotalBookNum = null;
    Button btnBookListAddBook= null;
    ListView listviewBookList = null;

    ArrayAdapter<dbBookHelper> booklistadapter;
    List<dbBookHelper> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls_book_list);
        editTextBookName = (EditText)findViewById(R.id.editBookName);
        editTextTotalBookNum = (EditText)findViewById(R.id.editTotalBookNumb);
        btnBookListAddBook = (Button)findViewById(R.id.btnclsAddBook);
        listviewBookList = (ListView)findViewById(R.id.listViewBooklist);

        btnBookListAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _sbookname = editTextBookName.getText().toString();
                String _stotalbook = editTextTotalBookNum.getText().toString();

                if( _sbookname.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter correct Book Name", Toast.LENGTH_LONG).show();
                }
                else if( _stotalbook.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter correct Total Book Number", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(TextUtils.isDigitsOnly(_stotalbook)) {
                        int _totalbooknum = Integer.parseInt(_stotalbook);
                        if (_totalbooknum != 0) {
                            dbBookHelper book = new dbBookHelper(_sbookname, _totalbooknum);
                            book.save();
                            clsBookupdateList();
                            clsBookclearForm();
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter valid total number", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Enter valid total number", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        clsBookupdateList();
    }

    private void clsBookupdateList()
    {
        // add it to adapter
        books = dbBookHelper.getAllBook();
        booklistadapter = new ArrayAdapter<dbBookHelper>(this, android.R.layout.simple_list_item_1, books);
        listviewBookList.setAdapter(booklistadapter);
    }

    private void clsBookclearForm()
    {
        editTextBookName.setTag(-1);
        editTextBookName.setText("");
        editTextTotalBookNum.setText("");
    }
}
