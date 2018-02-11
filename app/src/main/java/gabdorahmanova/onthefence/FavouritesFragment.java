package gabdorahmanova.onthefence;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 27.01.2018.
 */

public class FavouritesFragment extends Fragment {

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favourites, container, false);
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