package am.tk.wakeup;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvalutionFragment extends Fragment {


    public EvalutionFragment() {
        // Required empty public constructor
    }
    public static String TAG="EVALUATION"; 
    RecyclerView rvDates;
    long[] amount=new long[7];
    String[] type={"Food","Travel","Medicine","Groceries","Clothes","Enterrtainment","Electronics"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_evaluation, container, false);
        rvDates=view.findViewById(R.id.rvDates);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDates.setLayoutManager(layoutManager);
        ArrayList<Date> al=Date.getTheList();
        datesAdapter adapter=new datesAdapter(al);
        rvDates.setAdapter(adapter);
        DatabaseReference dbUsername= FirebaseDatabase.getInstance().getReference("username");
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        String date= DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        dbUsername.child(uid).child("date").child(date).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HashMap<String,Object> hashMap=(HashMap<String,Object>)dataSnapshot.getValue();
                amount[0]=(long)hashMap.get("Food");
                amount[1]=(long)hashMap.get("Travel");
                amount[2]=(long)hashMap.get("Medicine");
                amount[3]=(long)hashMap.get("Groceries");
                amount[4]=(long)hashMap.get("Clothes");
                amount[5]=(long)hashMap.get("Entertainment");
                amount[6]=(long)hashMap.get("Electronics");

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        List<PieEntry> pieEntries=new ArrayList<>();
        for(int i=0;i<amount.length;i++){
            pieEntries.add(new PieEntry(amount[i],type[i]));
        }
        PieDataSet pieDataSet=new PieDataSet(pieEntries,"Today's Expenditure");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData=new PieData(pieDataSet);
        PieChart chart= view.findViewById(R.id.chart);
        chart.setData(pieData);
        chart.animateY(1000);
        chart.invalidate();
        return view;
    }

    }



