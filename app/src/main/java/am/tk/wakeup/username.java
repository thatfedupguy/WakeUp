package am.tk.wakeup;


import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class username {
    HashMap<String,Integer> map;
    public username() {

    }

    public username( int food, int travel, int medicine, int groceries, int clothes, int entertainment, int electronics) {
        map=new HashMap<>();
        map.put("Food",food);
        map.put("Travel",travel);
        map.put("Medicine",medicine);
        map.put("Groceries",groceries);
        map.put("Clothes",clothes);
        map.put("Entertainment",entertainment);
        map.put("Electronics",electronics);



        //Calendar calendar=Calendar.getInstance();Food = food;Travel = travel;Medicine = medicine;Groceries = groceries;Clothes = clothes;Entertainment = entertainment;Electronics = electronic
    }


}
