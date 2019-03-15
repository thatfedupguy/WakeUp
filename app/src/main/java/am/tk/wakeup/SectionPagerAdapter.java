package am.tk.wakeup;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionPagerAdapter extends FragmentPagerAdapter{
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TrackerFragment trackerFragment=new TrackerFragment();
                return trackerFragment;
            case 1:
                EvalutionFragment evaluationfragment=new EvalutionFragment();
                return evaluationfragment;
            case 2:
                ReminderFragment reminderFragment=new ReminderFragment();
                return reminderFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Tracker";
            case 1: return "Evaluation";
            case 2: return "Reminder";
            default:return" ";
        }
    }
}
