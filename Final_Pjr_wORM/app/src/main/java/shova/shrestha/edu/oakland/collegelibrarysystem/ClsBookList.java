package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ClsBookList extends AppCompatActivity {

    EditText editTextBookName = null;
    Button btnBookListAddBook= null;
    ListView listviewBookList = null;

    ArrayAdapter<dbBookHelper> booklistadapter;
    List<dbBookHelper> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls_book_list);
        editTextBookName = (EditText)findViewById(R.id.editBookName);
        btnBookListAddBook = (Button)findViewById(R.id.btnclsAddBook);
        listviewBookList = (ListView)findViewById(R.id.listViewBooklist);

        btnBookListAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _sbookname = editTextBookName.getText().toString();

                dbBookHelper book = new dbBookHelper(_sbookname);
                book.save();
                clsBookupdateList();
                clsBookclearForm();

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
    }

}
