package gabdorahmanova.onthefence.Units;

import android.graphics.Bitmap;

/**
 * Created by admin on 18.02.2018.
 */

public class PostValue {
    private String time,heading,link;
    private Bitmap picture;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
