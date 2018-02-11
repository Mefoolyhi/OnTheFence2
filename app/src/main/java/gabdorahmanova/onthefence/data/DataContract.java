package gabdorahmanova.onthefence.data;

import android.provider.BaseColumns;

/**
 * Created by admin on 11.02.2018.
 */

public final class DataContract {
    private DataContract() {
    }


    public static final class GuestEntry implements BaseColumns {
        public final static String TABLE_NAME = "theatres";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_PICTURE = "picture";
        public final static String COLUMN_INFO = "info";
        public final static String COLUMN_HISTORY= "history";
        public final static String COLUMN_LINK = "link";

    }
}
