package am.tk.wakeup;


import android.Manifest;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {


    public ReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View Rview= inflater.inflate(R.layout.fragment_reminder, container, false);
        ListView lvMsgs=Rview.findViewById(R.id.lvMsgs);
        List<String> s;
        s = readAllMessages();
        String message = "";
        for(int i=0;i<s.size();i++){
            message += s.get(i) + "<";
        }
        String[] messageArray;
        messageArray = message.split("<");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,messageArray);
        lvMsgs.setAdapter(arrayAdapter);
        return Rview;
    }
    public List<String> readAllMessages(){
        List<String> sms=new ArrayList<>();
        Uri smsUri=null;
        int perm= ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS);
        if(perm== PackageManager.PERMISSION_GRANTED){
            smsUri=Uri.parse("content://sms/inbox");
        }
        else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_SMS},121);
        }

        Cursor cur = getActivity().getContentResolver().query(smsUri,null,null,null,null,null);
        while(cur.moveToNext()){
            String address=cur.getString(cur.getColumnIndexOrThrow("address"));
            String body=cur.getString(cur.getColumnIndexOrThrow("body"));
            if(address.equals("VK-IPAYTM")||address.equals("ERecharge"))
                sms.add(address + " : " + body);
        }
        return sms;
    }




}
