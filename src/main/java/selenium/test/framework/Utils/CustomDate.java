package selenium.test.framework.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CustomDate {

    private Properties p;

    public CustomDate (Properties properties){
        this.p = properties;
    }

    public String getFormattedDate(Date date){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(p.getProperty("base.time.format"));
        return simpleDateFormat.format(date);
    }
}