package shova.shrestha.edu.oakland.collegelibrarysystem;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;
@Table(name = "bookTable")
public class dbBookHelper extends Model {

    public static int bookflag = 0;

    @Column(name = "book_name")
    public String bookName;

    @Column(name = "total_BookNum")
    public int totalBookNum;

    @Column(name = "checkout_BookNum")
    public int checkoutBookNum;


    public dbBookHelper(){super();}
    public dbBookHelper(String _bookName, int _totalBook) {
        super();
        this.bookName = _bookName;
        this.checkoutBookNum = _totalBook;
        this.totalBookNum = _totalBook;
    }

    @Override
    public String toString() {
        if (bookflag == 1) {
            return getId() + ": " + bookName + "\n Tot Book : " + totalBookNum + "   Rem Book : " + checkoutBookNum;
        }
        else
        {
            return getId() + ": " + bookName ;
        }
    }

    public static List<dbBookHelper> getAllBook(){
        bookflag = 1;
        Select query = new Select();
        return query.from(dbBookHelper.class).orderBy("id").execute();
    }

    public static List<dbBookHelper> getAllBookName(){
        bookflag = 0;
        Select query = new Select();
        return query.from(dbBookHelper.class).orderBy("id").execute();
    }

    public static String getBookName(int _id)
    {
        Select BookNameQuery = new Select ();
        dbBookHelper bookName = BookNameQuery.from(dbBookHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return bookName.bookName;
    }

    public static int getTotalBookId(int _id)
    {
        Select BookNameQuery = new Select ();
        dbBookHelper bookName = BookNameQuery.from(dbBookHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return bookName.totalBookNum;
    }

    public static int getCheckoutBookId(int _id)
    {
        Select BookNameQuery = new Select ();
        dbBookHelper bookName = BookNameQuery.from(dbBookHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return bookName.checkoutBookNum;
    }

    public static int getMaxBookId()
    {
        Select query = new Select ();

        return query.from(dbBookHelper.class).orderBy("id").execute().size();
    }

    public static void decrementBookbyId(int _Bookid)
    {

        // boolean ret;
        int currentrembook = getCheckoutBookId(_Bookid);
        currentrembook = currentrembook -1;

        new Update(dbBookHelper.class)
                .set("checkout_BookNum = ?", currentrembook)
                .where("id = ?", _Bookid)
                .execute();

    }

    public static void incrementBookbyId(int _Bookid)
    {

        // boolean ret;
        int currentrembook = getCheckoutBookId(_Bookid);
        currentrembook = currentrembook + 1;

        new Update(dbBookHelper.class)
                .set("checkout_BookNum = ?", currentrembook)
                .where("id = ?", _Bookid)
                .execute();

    }


}

