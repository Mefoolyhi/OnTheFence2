package gabdorahmanova.onthefence;

import android.app.Fragment;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import gabdorahmanova.onthefence.data.DataHelper;


/**
 * Created by User on 27.01.2018.
 */

public class TheatresFragment extends Fragment {


    private final ArrayList<String> data = new ArrayList<>();
    public static final ArrayList<String> links = new ArrayList<>();
    private DataHelper helper;

    private Cursor c = null;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_news);




        helper = new DataHelper(getActivity());

        try {
            helper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try{
            helper.openDataBase();
        }catch (SQLException sqle) {
            throw sqle;
        }
        c = helper.query("theatres",null , null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                data.add(c.getString(1));
                links.add(c.getString(2));
            } while (c.moveToNext());
        }
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        MyAdapter mAdapter = new MyAdapter(data,getActivity());
        rv.setAdapter(mAdapter);
        return view;
    }
    public static TheatresFragment newInstance() {
        TheatresFragment fragment = new TheatresFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}