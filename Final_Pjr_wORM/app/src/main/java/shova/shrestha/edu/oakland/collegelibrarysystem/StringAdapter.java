package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class StringAdapter extends BaseAdapter {
    String[] data = {"Ama", "Abuwa", "Ata", "Aki", "Anirudh", "Suraj"};
    Context context = null;

    public StringAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return data.length/2;
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
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup group = (ViewGroup) inflater.inflate(android.R.layout.simple_list_item_2, null, false);

            TextView title = (TextView) group.getChildAt(0);
            TextView subtitle = (TextView) group.getChildAt(1);

            title.setText(data[2*i]);
            subtitle.setText(data[2*i+1]);

            return group;
        }
        return view;
    }
}
