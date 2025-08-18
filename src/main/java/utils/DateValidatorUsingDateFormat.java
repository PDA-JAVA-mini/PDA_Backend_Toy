package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidatorUsingDateFormat implements DateValidator {
    private String dateformat;

    public DateValidatorUsingDateFormat(String dateformat) {
        this.dateformat = dateformat;
    }

    @Override
    public boolean isValid(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        sdf.setLenient(false);
        try{
            sdf.parse(dateStr);
        }catch(ParseException e) {
            return false;
        }
        return true;
    }
}
