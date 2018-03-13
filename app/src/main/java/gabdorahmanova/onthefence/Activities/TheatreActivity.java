package gabdorahmanova.onthefence.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import gabdorahmanova.onthefence.R;
import gabdorahmanova.onthefence.Units.Theatre;
import gabdorahmanova.onthefence.data.DataTheatre;

public class TheatreActivity extends AppCompatActivity {


    TextView info,history;
    ImageView picture;
    Theatre theatre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);
        info = findViewById(R.id.info);
        history = findViewById(R.id.history);
        picture = findViewById(R.id.person_photo);


        int id = getIntent().getIntExtra("theatre",0);
        DataTheatre dt = new DataTheatre(getApplicationContext());
        theatre = dt.getTheatre(id);
        setTitle(theatre.getName());
        info.setText(theatre.getInfo());
        history.setText(theatre.getHistory());
        picture.setImageBitmap(theatre.getPicture());

    }
}

