package gabdorahmanova.onthefence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class TheatreActivity extends AppCompatActivity {


    TextView info,history;
    ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);
        info = findViewById(R.id.info);
        history = findViewById(R.id.history);
        picture = findViewById(R.id.person_photo);


        Theatre theatre = getIntent().getParcelableExtra("theatre");
        setTitle(theatre.name);
        info.setText(theatre.info);
        history.setText(theatre.history);
        picture.setImageBitmap(theatre.getPicture());

    }
}

