package gabdorahmanova.onthefence.Fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Adapters.NewsAdapter;
import gabdorahmanova.onthefence.Helpers.OnSwipeTouchListener;
import gabdorahmanova.onthefence.Helpers.ParsingClass;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.PostValue;

/**
 * Created by User on 27.01.2018.
 */


public class NewsFragment extends Fragment {

    View view;
    ArrayList<PostValue> news;
    RecyclerView rv;
    ProgressBar pb;
    TextView eror;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        rv = view.findViewById(R.id.rv_news);

        pb = view.findViewById(R.id.progressBar);
    eror = view.findViewById(R.id.eror_text);
    pb.setVisibility(View.INVISIBLE);
    eror.setVisibility(View.INVISIBLE);


        new MeTask().execute();
        return view;
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }


    class MeTask extends AsyncTask<Void, Void, Void> {
        boolean error = false;
        ParsingClass pc;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                pc = new ParsingClass();
                pc.get();
                news = pc.getPostsList();
            }
            catch (Exception e){
                error = true;
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (error == true){
                eror.setText("Проблемы с подключением к интернету");
                eror.setVisibility(View.VISIBLE);
            }
            else {
                try {
                    RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(llm);
                    rv.setAdapter(new NewsAdapter(news, getActivity()));
                } catch (Exception e) {
                    eror.setText("Проблемы с подключением к интернету");
                    eror.setVisibility(View.VISIBLE);
                }
            }
            pb.setVisibility(View.INVISIBLE);


        }



    }


}