package gabdorahmanova.onthefence.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Adapters.TheatresFragmentAdapter;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataHelper;
import gabdorahmanova.onthefence.data.DataTheatre;

/**
 * Created by User on 27.01.2018.
 */

public class FavouritesFragment extends Fragment {

    private View view;

    public FavouritesFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    RecyclerView rv;
    ArrayList<Theatre> data;

    @Override
    public void onResume() {
        super.onResume();

        DataTheatre dt = new DataTheatre(getActivity());
        data = dt.getFavourites();


        rv.setAdapter(new TheatresFragmentAdapter(data, getActivity(),"favourites"));
    }

    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

    view =  inflater.inflate(R.layout.fragment_news, container, false);
    rv = view.findViewById(R.id.rv_news);
    rv.setItemAnimator(new DefaultItemAnimator());
    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    DataTheatre dt = new DataTheatre(getActivity());
    data = dt.getFavourites();
    rv.setAdapter(new TheatresFragmentAdapter(data, getActivity(),"favourites"));
    return view;
        }

        public static FavouritesFragment newInstance() {
                FavouritesFragment fragment = new FavouritesFragment();
                return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        }
        }