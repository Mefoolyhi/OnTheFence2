package gabdorahmanova.onthefence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 18.02.2018.
 */

public class PostBaseAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<PostValue> postValuesArrayList;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private class ViewHolder{
        TextView tvTitle,tvDescr, tvDate,tvLink;

        public ViewHolder(View item){
            tvTitle = item.findViewById(R.id.news_title);
            tvDescr = item.findViewById(R.id.about);
            tvDate = item.findViewById(R.id.date);

        }
    }


}
