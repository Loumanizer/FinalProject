package shova.shrestha.edu.oakland.collegelibrarysystem;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by molly on 11/20/17.
 */


@Table(name = "studentTable")
public class dbStudentHelper extends Model {
    public static int studentflag = 0;

    @Column(name = "name")
    public String Name;

    @Column(name = "phonenumber")
    public String PhoneNumber;

    public dbStudentHelper(){super();}
    public dbStudentHelper(String _name, String _phnumber) {
        super();
        this.Name = _name;
        this.PhoneNumber = _phnumber;
    }

    @Override
    public String toString(){
        if (studentflag == 1) {
            return getId() + ": " + Name + ", " + PhoneNumber;
        }
        else
        {
            return getId() + ": " + Name;
        }
    }

    public static List<dbStudentHelper> getAllStudent(){
        studentflag = 1;
        Select query = new Select();
        return query.from(dbStudentHelper.class).orderBy("name").execute();
    }

    public static List<dbStudentHelper> getAllStudentName(){
        studentflag = 0;
        Select query = new Select();
        return query.from(dbStudentHelper.class).orderBy("id").execute();
    }

    public static String getStudentName(int _id)
    {
        Select StudentNameQuery = new Select ();
        dbStudentHelper studentName = StudentNameQuery.from(dbStudentHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return studentName.Name;
    }

    public static String getStudentPhoneNumber(int _id)
    {
        Select StudentNameQuery = new Select ();
        dbStudentHelper studentName = StudentNameQuery.from(dbStudentHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return studentName.PhoneNumber;
    }
}
