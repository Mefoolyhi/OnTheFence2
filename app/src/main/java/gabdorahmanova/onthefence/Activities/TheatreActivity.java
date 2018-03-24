package gabdorahmanova.onthefence.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import gabdorahmanova.onthefence.Adapters.ScheduleAdapter;
import gabdorahmanova.onthefence.Fragments.FavouritesFragment;
import gabdorahmanova.onthefence.Helpers.OnSwipeTouchListener;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Performance;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataTheatre;

public class TheatreActivity extends AppCompatActivity {


    TextView info,helper,defaultt,number,site;
    SimpleDraweeView picture;
    Theatre theatre;
    ListView schedule;
    Integer fav;
    ImageButton favourite;
    ArrayList<String> titlelist = new ArrayList<>();
    ProgressBar pb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theatre);
        info = findViewById(R.id.info);

        picture = findViewById(R.id.person_photo);
        helper = findViewById(R.id.helper);
        number = findViewById(R.id.number);
        site = findViewById(R.id.site);
        schedule = findViewById(R.id.schedule);
        defaultt = findViewById(R.id.default_text);
        favourite = findViewById(R.id.favourite);
        pb = findViewById(R.id.progressBar);


        RelativeLayout rl = findViewById(R.id.rl);
        rl.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeRight() {
                finish();
            }

            @Override
            public void onSwipeLeft() {

            }
        });

        int i = getIntent().getIntExtra("favourites", -1);
         int id = getIntent().getIntExtra("theatres", -1);




        DataTheatre dt = new DataTheatre(getApplicationContext());

        try {
            theatre = dt.getTheatre(id);

        }catch (Exception e){

            theatre = dt.getFromFavourites(i);
            Log.e("TheatreActivity",e.getMessage());

        }
        setTitle(theatre.getName());
        info.setText(theatre.getInfo());
        number.setText(theatre.getNumber());
        site.setText(theatre.getSite());
        fav = theatre.getFav();
        defaultt.setText("Репертуар на сезон пока отсутствует");
        if (!fav.equals(-1)){
            favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
            Log.e("fav",fav.toString());
        }




        new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
            @Override
            public void run() {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            picture.setImageURI(Uri.parse(theatre.getPiclink()));
                            helper.setVisibility(View.INVISIBLE);
                                                      }
                    });

            }
        }).start();


        pb.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String URL = theatre.getLink();
                    final Document doc = Jsoup.connect(URL).get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Elements content = doc.select("tr");



                            for (int i = 0; i < content.size(); i++) {
                                Element row = content.get(i);
                                Elements cols = row.select("td");
                                for (int j = 1; j <= cols.size();j++){
                                    Element e = cols.get(j-1);
                                    if (j % 5 != 0)
                                            titlelist.add(e.text());
                                    else if (j % 4 != 0 && j % 2 == 0){

                                        titlelist.add(e.select(".title").text());
                                    }
                                    else
                                    {
                                        try {
                                            Element element = e.selectFirst("a[href]");
                                            titlelist.add(element.attr("abs:href"));
                                        } catch (Exception ex) {
                                            Log.e("TA165", ex.getMessage());
                                        }

                                    }

                                }
                            }
                            if (titlelist.isEmpty()){
                                schedule.setVisibility(View.INVISIBLE);
                                defaultt.setVisibility(View.VISIBLE);
                            }
                            else{
                                ArrayList<Performance> data = new ArrayList<>();

                                for (int i = 0; i < titlelist.size() - 5; i += 5){
                                    Performance pr = new Performance(titlelist.get(i+1),titlelist.get(i+3),theatre.getName(),titlelist.get(i),titlelist.get(i+2),titlelist.get(i+4));
                                    Log.e("Per",pr.toString());
                                    data.add(pr);
                                }


                                schedule.setAdapter(new ScheduleAdapter(getApplicationContext(),data));

                                pb.setVisibility(View.INVISIBLE);

                            }

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    defaultt.setText("Проблемы с подключением к Интернету");
                    defaultt.setVisibility(View.VISIBLE);
                }
            }
        }).start();



        //если нажимать на одной активности несколько раз - не удаляет. я хз, как это, исправь потом
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fav != -1){
                    try {
                        DataTheatre dt = new DataTheatre(getApplicationContext());
                        dt.deleteFromFavourites(theatre.getFav());
                        favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        fav = -1;
                        dt.updateStatus(theatre, -1);


                    }
                    catch (Exception e){
                        Log.e("Theatre",e.getMessage());
                    }


                }else{
                    DataTheatre dt = new DataTheatre(getApplicationContext());
                    dt.addToFavourites(theatre,theatre.getId()-1);
                    favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
                    fav = dt.getFavourites().size() ;
                    dt.updateStatus(theatre,fav);

                }

            }
        });


    }

}

