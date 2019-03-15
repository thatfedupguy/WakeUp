package am.tk.wakeup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1000;
    private static final String TAG ="Main";
    private ViewPager viewPager;
    private SectionPagerAdapter sectionPagerAdapter;
    private FragmentManager fragmentManager;
    private FirebaseAuth mAuth;
    private TabLayout main_tabs;
    Intent intent=getIntent();

    Toolbar main_app_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Firebase
        mAuth = FirebaseAuth.getInstance();
        //Initializing variables


        main_app_bar=findViewById(R.id.main_app_bar);
        setSupportActionBar(main_app_bar);
        getSupportActionBar().setTitle("Wake Up");
        //Tabs
        viewPager=findViewById(R.id.main_view_pager);
        sectionPagerAdapter=new SectionPagerAdapter(getSupportFragmentManager());
        main_tabs=findViewById(R.id.main_tabs);
        main_tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(sectionPagerAdapter);



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            send_to_start();
        }else{
            //logged in
        }
    }

    private void send_to_start() {
        Intent startIntent =new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_btn){
            FirebaseAuth.getInstance().signOut();
            send_to_start();
        }
        return true;
    }
}
