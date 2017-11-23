package shova.shrestha.edu.oakland.collegelibrarysystem;

/**
 * Created by molly on 11/21/17.
 */

public class BookCheckout {
    public String getBoookName() {
        return bookname;
    }

    public void setBookName(String _bookname) {
        this.bookname = _bookname;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    private String bookname = null;
    private String duedate = null;

    public BookCheckout(String bookname, String duedate) {
        this.bookname = bookname;
        this.duedate = duedate;
    }
}

