package gabdorahmanova.onthefence.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Adapters.TheatresFragmentAdapter;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataTheatre;


/**
 * Created by User on 27.01.2018.
 */

public class TheatresFragment extends Fragment {



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private View view;
    public TheatresFragment(){}


    RecyclerView rv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.fragment_news, container, false);
        rv = view.findViewById(R.id.rv_news);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
            DataTheatre dt = new DataTheatre(getActivity());
        ArrayList<Theatre> data = dt.getData();
        TheatresFragmentAdapter mAdapter = new TheatresFragmentAdapter(data, getActivity(),"theatres");
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

    @Override
    public void onResume() {
        super.onResume();
        DataTheatre dt = new DataTheatre(getActivity());
        ArrayList<Theatre> data = dt.getData();
        TheatresFragmentAdapter mAdapter = new TheatresFragmentAdapter(data, getActivity(),"theatres");
        rv.setAdapter(mAdapter);
    }
}