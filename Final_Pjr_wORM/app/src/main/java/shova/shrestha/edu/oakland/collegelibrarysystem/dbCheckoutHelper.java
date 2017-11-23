package shova.shrestha.edu.oakland.collegelibrarysystem;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.sql.Date;
import java.util.List;

/**
 * Created by molly on 11/20/17.
 */


@Table(name = "checkoutTable")
public class dbCheckoutHelper extends Model {

    @Column(name = "student_id")
    public int studentId;

    @Column(name = "book_id")
    public int bookId;

    @Column(name = "DueDate")
    public String dueDate;


    public dbCheckoutHelper(){super();}
    public dbCheckoutHelper(int _studentId, int _gameId, String _dueDate ) {
        super();
        this.studentId = _studentId;
        this.bookId = _gameId;
        this.dueDate = _dueDate;
    }

    @Override
    public String toString(){
        //String ret = getId() + ":" + studentId + "," + bookId + "," + dueDate;
        String _stdname = dbStudentHelper.getStudentName(studentId);
        String _bookname = dbBookHelper.getBookName(bookId);
        //return  ret;
        return "ID: " + getId() + ": STD Name: " + _stdname + ", Book name: " + _bookname + ",Due Date: " + dueDate;
    };

    public static List<dbCheckoutHelper> getAllCheckout(){
        Select query = new Select();
        return query.from(dbCheckoutHelper.class).orderBy("DueDate").execute();
    }

    public static int getStudentId(int _id)
    {
        Select CheckoutQuery = new Select ();
        dbCheckoutHelper studentid = CheckoutQuery.from(dbCheckoutHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return studentid.studentId;
    }

    public static int getBookId(int _id)
    {
        Select CheckoutQuery = new Select ();
        dbCheckoutHelper studentid = CheckoutQuery.from(dbCheckoutHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return studentid.bookId;
    }

    public static String getDuedate(int _id)
    {
        Select CheckoutQuery = new Select ();
        dbCheckoutHelper studentid = CheckoutQuery.from(dbCheckoutHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return studentid.dueDate;
    }

    public static int getChecoutBookById(int index, int _studentId){
        Select query = new Select();
        Select CheckoutQuery = new Select ();
        dbCheckoutHelper checkout = CheckoutQuery.from(dbCheckoutHelper.class)
                .where("id = ?", index)
                .executeSingle();
        if(checkout.studentId ==_studentId )
        {
            return checkout.bookId;
        }
        else {
            return -1;
        }
    }

    public static int getCheckoutbookCount()
    {
        Select query = new Select();
        return query.from(dbCheckoutHelper.class).orderBy("DueDate").execute().size();
    }
}
