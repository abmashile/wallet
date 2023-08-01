package za.co.wallet.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ApplicationDateUtil {

    public static int getAge(Date dateOfBirth){
        long diffInMillis = new Date().getTime() - dateOfBirth.getTime();
        return (int) (diffInMillis/ 31556926000l);
    }
}
