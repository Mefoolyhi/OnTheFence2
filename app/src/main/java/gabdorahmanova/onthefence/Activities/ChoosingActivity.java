package gabdorahmanova.onthefence.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;

import gabdorahmanova.onthefence.R;

public class ChoosingActivity extends AppCompatActivity {

    ProgressBar pb;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);
        Button choose = findViewById(R.id.choose_the_date);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getFragmentManager(), "datePicker");
            }
        });
        pb = findViewById(R.id.progressBar);
error = findViewById(R.id.error);
error.setText("Что-то пошло не так, попробуйте снова чуть позже");
pb.setVisibility(View.INVISIBLE);
error.setVisibility(View.INVISIBLE);

    }

    ArrayList<String> links_for_more = new ArrayList<>(),titles = new ArrayList<>(),links_jpeg = new ArrayList<>(),time = new ArrayList<>();


    class MeTask extends AsyncTask<Void, Void, Void> {
        boolean dead = false;

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc = Jsoup.connect(link).get();
                Elements e = doc.select("table").select("a[href]").select(".big_orange");
                links_for_more.clear();
                for (Element con:e){
                    if (!con.attr("abs:href").equals(""))
                        links_for_more.add(con.attr("abs:href"));
                }
                e = doc.select("table").select("b").select(".big_orange");
                titles.clear();
                for (Element con : e){
                    titles.add(con.text());
                }
                e = doc.select("table").select("p").select("a[href]").select("img");
                links_jpeg.clear();
                for (Element con: e){
                    links_jpeg.add(con.absUrl("src"));
                }
                e = doc.select("table").select("table").select("td");
                time.clear();
                for (Element con: e){
                    if (con.text().contains("Начало:"))
                        time.add(con.text());
                }



                Log.e(String.valueOf(links_for_more.size()),links_for_more.toString());
                Log.e(String.valueOf(time.size()),time.toString());
                Log.e(String.valueOf(titles.size()),titles.toString());
                Log.e(String.valueOf(links_jpeg.size()),links_jpeg.toString());
            } catch (Exception e) {
                Log.e("Choose", e.getMessage());
                dead = true;

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

            pb.setVisibility(View.INVISIBLE);
            if (dead == true){
                error.setVisibility(View.VISIBLE);
            }

        }

    }





String link;

@SuppressLint("ValidFragment")
class DatePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // определяем текущую дату
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Dialog picker = new DatePickerDialog(getActivity(), this,
                year, month, day);


        return picker;
    }


    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {

        TextView tv = getActivity().findViewById(R.id.date_whic_choosed);
        month++;
        tv.setText(day + "." + month + "." + year);
        String d,m;
        if (day <= 9){
            d = '0'+ String.valueOf(day);
        }
        else
            d = String.valueOf(day);
        if (month <= 9){
            m = '0' + String.valueOf(month);
        }
        else
            m = String.valueOf(month);

        link = "https://www.e1.ru/afisha/events/" + year+"-"+ m + "-" + d+"/theatre/";

        new MeTask().execute();

    }
}}