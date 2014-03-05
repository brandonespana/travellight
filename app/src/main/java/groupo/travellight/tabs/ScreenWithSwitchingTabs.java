package groupo.travellight.tabs;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;


import groupo.travellight.app.R;

/**
 * Created by Brandon on 3/4/14.
 */
public class ScreenWithSwitchingTabs extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);


        Fragment helpFragment = new FragmentHelpScreen();
        Fragment notifFragment = new FragmentNotificationScreen();

        actionBar.addTab(actionBar.newTab().setText("Help Screen").setTabListener(new theTabListener(helpFragment)));
        actionBar.addTab(actionBar.newTab().setText("Notification Screen").setTabListener(new theTabListener(notifFragment)));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_help:
               // goToHelp();
                return true;
            case R.id.action_home:
               // goToMain();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected class theTabListener implements ActionBar.TabListener{

        private Fragment fragment;

        public theTabListener(Fragment fragment){
            this.fragment=fragment;
        }
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
                ft.add(R.id.container, fragment, null);
        }
        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){

        }
        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){
                ft.remove(fragment);
        }
    }//end theTabListener class

}
