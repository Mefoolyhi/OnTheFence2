package gabdorahmanova.onthefence.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import gabdorahmanova.onthefence.Adapters.ScheduleAdapter;
import gabdorahmanova.onthefence.Fragments.FavouritesFragment;
import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Performance;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataTheatre;

public class TheatreActivity extends AppCompatActivity {


    TextView info,helper,defaultt,number,site;
    ImageView picture;
    Theatre theatre;
    ListView schedule;
    Integer fav;
    ImageButton favourite;
    ArrayList<String> titlelist = new ArrayList<>();

    LruCache<String, Bitmap> mMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);
        info = findViewById(R.id.info);
       // history = findViewById(R.id.history);
        picture = findViewById(R.id.person_photo);
        helper = findViewById(R.id.helper);
        number = findViewById(R.id.number);
        site = findViewById(R.id.site);
        schedule = findViewById(R.id.schedule);
        defaultt = findViewById(R.id.default_text);
        favourite = findViewById(R.id.favourite);


        int i = getIntent().getIntExtra("favourites", -1);
         int id = getIntent().getIntExtra("theatres", -1);




        DataTheatre dt = new DataTheatre(getApplicationContext());

        try {
            theatre = dt.getTheatre(id);

        }catch (Exception e){

            theatre = dt.getFromFavourites(i);
            Log.e("TheatreActivity",e.getMessage());

        }
        Log.e("TheatreActivity",theatre.toString());
        setTitle(theatre.getName());
        info.setText(theatre.getInfo());
        number.setText(theatre.getNumber());
        site.setText(theatre.getSite());
        fav = theatre.getFav();
        if (!fav.equals(-1)){
            favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
            Log.e("fav",fav.toString());
        }

        mMemoryCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()) / 8) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };


        new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
            @Override
            public void run() {
                try {
                    URL url = new URL(theatre.getPiclink());

                    Bitmap pic = getBitmapFromMemCache(url.toString());

                    if (pic == null) {
                        pic = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        addBitmapToMemoryCache(url.toString(),pic);
                    }

                    final Bitmap finalPic = pic;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            theatre.setPicture(finalPic);
                            helper.setVisibility(View.INVISIBLE);
                            picture.setImageBitmap(finalPic);                           }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
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
                                for (Element e: cols){
                                    if (e.hasAttr("colspan")) {
                                        int colspan = Integer.valueOf(e.attr("colspan"));
                                        for (int y = 0; y < colspan; y++){
                                            titlelist.add(e.text().toString());
                                        }
                                    } else {
                                        titlelist.add(e.text().toString());
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
                                    Performance pr = new Performance(titlelist.get(i+1),titlelist.get(i+3),theatre.getName(),titlelist.get(i),titlelist.get(i+2));

                                    data.add(pr);
                                }


                                schedule.setAdapter(new ScheduleAdapter(getApplicationContext(),data));

                            }

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
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

    void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            Log.e("Cache",key);
            mMemoryCache.put(key, bitmap);
        }
    }

    Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


}

