package am.tk.wakeup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class msgs {
    String Address;
    String body;

    public msgs(String address, String body) {
        Address = address;
        this.body = body;
    }

    public String getAddress() {
        return Address;
    }

    public String getBody() {
        return body;
    }
    public ArrayList<msgs> getMsgsList(){
        List<String> s;
        ArrayList<msgs> list = new ArrayList<>();

        s = readAllMessages();
        String message = "";
        for(int i=0;i<s.size();i++){
            message += s.get(i) + ",";
        }
        String[] messageArray;
        messageArray = message.split(",");
        for(int i=0;i<messageArray.length;i++){
            String[] amessage = messageArray[i].split(":");
            msgs msgs=new msgs(amessage[0],amessage[1]);
            list.add(msgs);
        }
        return list;


    }
    public List<String> readAllMessages(){
        List<String> sms=new ArrayList<>();
        Uri smsUri=null;
        int perm= ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_SMS);
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
            if(address.equals("VK-IPAYTM"))
                sms.add(address + " : " + body);
        }
        return sms;
    }
    private Context mContext;
    public msgs(Context context) {
        mContext=context;
    }
    public Activity getActivity() {
        Context context = mContext;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }


}