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

    public int getCheckoutId() {
        return ckoutid;
    }

    public void setCheckoutId(int _checkoutId) {
        this.ckoutid = _checkoutId;
    }

    private String bookname = null;
    private String duedate = null;
    private int ckoutid = 0;

    public BookCheckout(int ckoutid, String bookname, String duedate) {
        this.bookname = bookname;
        this.duedate = duedate;
        this.ckoutid = ckoutid;
    }
}

