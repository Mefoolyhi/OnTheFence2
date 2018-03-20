package gabdorahmanova.onthefence.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataTheatre;

public class TheatreActivity extends AppCompatActivity {


    TextView info,history,helper,defaultt;
    ImageView picture;
    Theatre theatre;
    GridView schedule;

    ArrayList<String> titlelist = new ArrayList<>();

    LruCache<String, Bitmap> mMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);
        info = findViewById(R.id.info);
        history = findViewById(R.id.history);
        picture = findViewById(R.id.person_photo);
        helper = findViewById(R.id.helper);
        schedule = findViewById(R.id.schedule);
        defaultt = findViewById(R.id.default_text);


        int id = getIntent().getIntExtra("theatre",0);
        DataTheatre dt = new DataTheatre(getApplicationContext());
        theatre = dt.getTheatre(id);
        setTitle(theatre.getName());
        info.setText(theatre.getInfo());
        history.setText(theatre.getHistory());

        mMemoryCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()) / 8) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };

        final String URL = theatre.getLink();
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


                    Document doc = Jsoup.connect(URL).get();
                    Log.e("Document",doc.toString());
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
                    Log.e("Element", titlelist.toString());
                    if (titlelist.isEmpty()){
                        schedule.setVisibility(View.INVISIBLE);
                        defaultt.setVisibility(View.VISIBLE);

                    }







                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }

    void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


}

