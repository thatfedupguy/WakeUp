package am.tk.wakeup;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Date {
    String date;
    String day;
    String month;
    String year;

    public Date(String date, String day, String month, String year) {
        this.date = date;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
    public static String[] days={"monday","tuesday","wednesday","thursday","friday","saturday","sunday"
    };

    public static ArrayList<Date> getTheList(){
        ArrayList<Date> al=new ArrayList<>();
        String data= DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        int i=Integer.valueOf(data.substring(0,2));
        int j=4;
        while(i<31){
            String date=String.valueOf(i);
            String month=data.substring(2,data.length()-5);
            String year=data.substring(data.length()-5);
            if(j>6){
                j=0;
            }
            Date date1=new Date(date,days[j],month,year);
            j++;
            al.add(date1);
            i++;
        }


        return al;
    }
}
