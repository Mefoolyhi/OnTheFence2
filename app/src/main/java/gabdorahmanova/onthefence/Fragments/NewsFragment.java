package gabdorahmanova.onthefence.Fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Adapters.NewsAdapter;
import gabdorahmanova.onthefence.ParsingClass;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.PostValue;

/**
 * Created by User on 27.01.2018.
 */


public class NewsFragment extends Fragment {

    View view;
    ArrayList<PostValue> news;
    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        rv = view.findViewById(R.id.rv_news);




        new MeTask().execute();
        return view;
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }


    class MeTask extends AsyncTask<Void, Void, Void> {
        ParsingClass pc;

        @Override
        protected Void doInBackground(Void... voids) {
            pc = new ParsingClass();

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
            rv.setAdapter(new NewsAdapter(news,getContext()));

        }



    }


}