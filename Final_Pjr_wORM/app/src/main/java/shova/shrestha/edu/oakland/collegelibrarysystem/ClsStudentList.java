package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Update;

import java.util.ArrayList;
import java.util.List;

public class ClsStudentList extends AppCompatActivity {
    EditText editTextStdName = null;
    EditText editTextStdPhoneNumb = null;
    Button btnStudentListAddStudent= null;
    Button btnStudentListUpdateStudent= null;
    Button btnStudentListDetailStudent= null;
    ListView listviewStudentList = null;

    ArrayAdapter<dbStudentHelper> studentlistadapter;
    List<dbStudentHelper> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls_student_list);

        editTextStdName = (EditText)findViewById(R.id.editStudentName);
        editTextStdPhoneNumb = (EditText)findViewById(R.id.editStudentPhoneNumber);
        btnStudentListAddStudent = (Button)findViewById(R.id.btnStdLstAddStudent);
        btnStudentListUpdateStudent = (Button)findViewById(R.id.btnStdLstUpdateStudent);
        btnStudentListDetailStudent = (Button)findViewById(R.id.btnStdLstDetailStudent);
        listviewStudentList = (ListView)findViewById(R.id.listViewStudentList);


        // disable delete btn and updated btn in begining of on create
        btnStudentListDetailStudent.setEnabled(false);
        btnStudentListUpdateStudent.setEnabled(false);


        listviewStudentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView) view).getText().toString();
                String[] split = item.split(": ");
                int _studentId = Integer.parseInt(split[0]);
                split = split[1].split(", ");
                split[0].trim();
                split[1].trim();
                editTextStdName.setText(split[0]);
                editTextStdPhoneNumb.setText(split[1]);
                editTextStdName.setTag(_studentId);
                btnStudentListDetailStudent.setEnabled(true);
                btnStudentListUpdateStudent.setEnabled(true);
            }
        });

        btnStudentListAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _studentname = editTextStdName.getText().toString();
                String _studentPhonenumber = editTextStdPhoneNumb.getText().toString();

                dbStudentHelper student = new dbStudentHelper(_studentname, _studentPhonenumber);
                student.save();
                clsStudentupdateList();
                clsStudentclearForm();

            }
        });

        btnStudentListUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _name = editTextStdName.getText().toString();
                String _phoneNumber = editTextStdPhoneNumb.getText().toString();
                int _studentId = (int) editTextStdName.getTag();

                // Update record
                new Update(dbStudentHelper.class)
                        .set("name = ?, phonenumber = ?", _name, _phoneNumber)
                        .where("id = ?", _studentId)
                        .execute();

                clsStudentupdateList();
                clsStudentclearForm();
            }
        });

        btnStudentListDetailStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _selectedStudentId =  (int) editTextStdName.getTag();
                Intent intent = new Intent(ClsStudentList.this, StudentDetail.class);
                intent.putExtra("STUDENT_ID",_selectedStudentId);
                startActivity(intent);
            }
        });
        clsStudentupdateList();
    }

    private void clsStudentupdateList()
    {
        // add it to adapter
        students = dbStudentHelper.getAllStudent();
        studentlistadapter = new ArrayAdapter<dbStudentHelper>(this, android.R.layout.simple_list_item_1, students);
        listviewStudentList.setAdapter(studentlistadapter);
    }

    private void clsStudentclearForm()
    {
        editTextStdName.setTag(-1);
        editTextStdName.setText("");
        editTextStdPhoneNumb.setText("");
        btnStudentListDetailStudent.setEnabled(false);
        btnStudentListUpdateStudent.setEnabled(false);
    }

}
