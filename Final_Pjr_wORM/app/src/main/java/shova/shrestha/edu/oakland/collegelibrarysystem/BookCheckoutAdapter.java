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



public class BookCheckoutAdapter extends BaseAdapter {
    ArrayList<BookCheckout> bookCheckOut = new ArrayList<>();

    void addCheckoutBook(int cCheckoutId, String cBookName, String cIssuedate, String cDuedate)
    {
        BookCheckout bookcheckout = new BookCheckout(cCheckoutId, cBookName, cIssuedate, cDuedate);
        bookCheckOut.add(bookcheckout);
    }

    public String getCheckoutBookName(int i){return  bookCheckOut.get(i).getBoookName();}
    public String getCheckoutIssuedate(int i){return  bookCheckOut.get(i).getIssuedate();}
    public String getCheckoutDuedate(int i){return  bookCheckOut.get(i).getDuedate();}
    public int getCheckoutId(int i){return  bookCheckOut.get(i).getCheckoutId();}

    void updateBookCheckout(int i, int cCheckoutId, String cBookName, String cIssuedate, String cDuedate)
    {
        BookCheckout checkoutBook = new BookCheckout(cCheckoutId, cBookName, cIssuedate, cDuedate);
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

            TextView txtbookName = (TextView) layout.findViewById(R.id.textstudentbookcheckout);
            TextView txtissuedate = (TextView) layout.findViewById(R.id.textstudentduedateIssuedate);
            TextView txtduedate = (TextView) layout.findViewById(R.id.textstudentduedatecheckout);

            txtbookName.setText(bookCheckOut.get(i).getBoookName());
            txtissuedate.setText(bookCheckOut.get(i).getIssuedate());
            txtduedate.setText(bookCheckOut.get(i).getDuedate());

            return layout;
        }
        return view;
    }

    public void add(String s) {
    }
}
