package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by molly on 11/21/17.
 */

public class BookCheckoutAdapter extends BaseAdapter {
    ArrayList<BookCheckout> bookCheckOut = new ArrayList<>();

    void addCheckoutBook(int cCheckoutId, String cBookName, String cDuedate)
    {
        BookCheckout bookcheckout = new BookCheckout(cCheckoutId, cBookName, cDuedate);
        bookCheckOut.add(bookcheckout);
    }

    public String getCheckoutBookName(int i){return  bookCheckOut.get(i).getBoookName();}
    public String getCheckoutDuedate(int i){return  bookCheckOut.get(i).getDuedate();}
    public int getCheckoutId(int i){return  bookCheckOut.get(i).getCheckoutId();}

    void updateBookCheckout(int i, int cCheckoutId, String cBookName, String cDuedate)
    {
        BookCheckout checkoutBook = new BookCheckout(cCheckoutId, cBookName, cDuedate);
        bookCheckOut.set(i, checkoutBook);
    }

    // public int getIndex() {return  products.get()}

    void removeCheckoutBook(int i) {
        bookCheckOut.remove(i);
    }

    @Override
    public int getCount() {
        return bookCheckOut.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_student_book_check_out,  null, false);

            TextView bookName = (TextView) layout.findViewById(R.id.textstudentbookcheckout);
            TextView duedate = (TextView) layout.findViewById(R.id.textstudentduedatecheckout);

            bookName.setText(bookCheckOut.get(i).getBoookName());
            duedate.setText(bookCheckOut.get(i).getDuedate());

            return layout;
        }
        return view;
    }

    public void add(String s) {
    }
}
