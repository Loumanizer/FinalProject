package shova.shrestha.edu.oakland.collegelibrarysystem;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by molly on 11/20/17.
 */


@Table(name = "bookTable")
public class dbBookHelper extends Model {

    @Column(name = "book_name")
    public String bookName;


    public dbBookHelper(){super();}
    public dbBookHelper(String _bookName) {
        super();
        this.bookName = _bookName;
    }

    @Override
    public String toString(){
        return getId() + ": " + bookName;
    }

    public static List<dbBookHelper> getAllBook(){
        Select query = new Select();
        return query.from(dbBookHelper.class).orderBy("book_name").execute();
    }

    public static String getBookName(int _id)
    {
        Select BookNameQuery = new Select ();
        dbBookHelper bookName = BookNameQuery.from(dbBookHelper.class)
                .where("id = ?", _id)
                .executeSingle();
        return bookName.bookName;
    }
}

