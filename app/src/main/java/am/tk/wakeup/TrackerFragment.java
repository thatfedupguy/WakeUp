package am.tk.wakeup;


import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackerFragment extends Fragment {
    final Calendar calendar=Calendar.getInstance();
    final String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

    final String date= DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
    username user=new username(0,0,0,0,0,0,0);
    private AutoCompleteTextView tracker_actv_track;
    private EditText tracker_et_money;
    private Button tracker_add_btn;
    public static final String TAG="tk";

    String[] type_of_ependiture = {"Food","Travel","Entertainment","Groceries","Medicines","Clothes","Electronics",};
    public void clear(){
        tracker_et_money.setText("");
        tracker_actv_track.setText("");
    }
    final DatabaseReference dbUsername=FirebaseDatabase.getInstance().getReference("username");
    String id=dbUsername.push().getKey();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_tracker, container, false);





        tracker_actv_track=view.findViewById(R.id.tracker_actv_track);
        tracker_add_btn=view.findViewById((R.id.tracker_add_btn));
        tracker_et_money=view.findViewById(R.id.tracker_et_money);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getContext(),android.R.layout.select_dialog_item,type_of_ependiture);
        tracker_actv_track.setThreshold(1);
        tracker_actv_track.setAdapter(arrayAdapter);


        dbUsername.child(uid).child("date").child(date).setValue(user);
        tracker_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money=Integer.valueOf(tracker_et_money.getText().toString());
                String track=tracker_actv_track.getText().toString();
                if(track.equals("Food")){
                    update("Food",money);

                }
                else if(track.equals("Travel")){
                    update("Travel",money);
                }
                else if(track.equals("Medicine")) {

                    update("Medicine",money);
                }
                else if(track.equals("Clothes")) {

                    update("Clothes",money);
                }
                else if(track.equals("Groceries")){
                    update("Groceries",money);


                }else if(track.equals("Entertainment")){
                    update("Entertainment",money);

                }else if(track.equals("Electronics")){
                    update("Electronics",money);

                }
                clear();
            }
        });



        return view;
    }
    public void update(String str,int money){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("username").child(uid).child("date").child(date);
        username username=user;
        username.map.put(str,money+user.map.get(str));
        databaseReference.setValue(username);
    }

}
