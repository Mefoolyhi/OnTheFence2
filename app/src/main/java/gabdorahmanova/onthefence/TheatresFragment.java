package gabdorahmanova.onthefence;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by User on 27.01.2018.
 */

public class TheatresFragment extends Fragment {



    private final ArrayList<String> data = new ArrayList<String>();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.fragment_theatres, container, false);
        RecyclerView rv = view.findViewById(R.id.rv);




        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        MyAdapter mAdapter = new MyAdapter(data);
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