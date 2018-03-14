package gabdorahmanova.onthefence.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataTheatre;

public class TheatreActivity extends AppCompatActivity {


    TextView info,history,helper;
    ImageView picture;
    Theatre theatre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);
        info = findViewById(R.id.info);
        history = findViewById(R.id.history);
        picture = findViewById(R.id.person_photo);
        helper = findViewById(R.id.helper);


        int id = getIntent().getIntExtra("theatre",0);
        DataTheatre dt = new DataTheatre(getApplicationContext());
        theatre = dt.getTheatre(id);
        setTitle(theatre.getName());
        info.setText(theatre.getInfo());
        history.setText(theatre.getHistory());
        new Thread(new Runnable() {//новый поток для работы с сетью. Иначе рабоать не будет!
            @Override
            public void run() {
                try {
                    URL url = new URL(theatre.getPiclink());
                    final Bitmap pic = BitmapFactory.decodeStream(url.openConnection().getInputStream()); // полчаем картинку по ссылке
                    runOnUiThread(new Runnable() { // с визуальными элментами можем работать только в главном потоке! Тут нам помогает контект.
                        @Override
                        public void run() {
                            theatre.setPicture(pic);
                            helper.setVisibility(View.INVISIBLE);
                            picture.setImageBitmap(pic);                           }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}

