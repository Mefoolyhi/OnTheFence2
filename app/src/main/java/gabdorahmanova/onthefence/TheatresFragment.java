package gabdorahmanova.onthefence;

import android.annotation.SuppressLint;
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

@SuppressLint("ValidFragment")
public class TheatresFragment extends Fragment {



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private View view;
    private ArrayList<Theatre> data;
    @SuppressLint("ValidFragment")
    TheatresFragment(ArrayList<Theatre> data){
        this.data = data;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_news);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
        MyAdapter mAdapter = new MyAdapter(data, getActivity());
            rv.setAdapter(mAdapter);
        return view;
    }
    public static TheatresFragment newInstance(ArrayList<Theatre> data) {
        TheatresFragment fragment = new TheatresFragment(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}