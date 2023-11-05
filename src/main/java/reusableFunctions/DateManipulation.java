package reusableFunctions;

import org.apache.commons.lang3.time.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManipulation {

    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");

    public String getTodaysDate(){
        Date date = new Date();
        String strDate = formatter.format(date);
        System.out.println("Date Format with dd MMMM yyyy : " + strDate);
        return  strDate;
    }

    public String addMonthstoDate( int monthsToAdd){
        Date newDate = DateUtils.addMonths(new Date(), monthsToAdd);
        formatter = new SimpleDateFormat("MMM dd yyyy");
        String strDate_1 = formatter.format(newDate);
        System.out.println(strDate_1);
        return strDate_1;
    }

}
