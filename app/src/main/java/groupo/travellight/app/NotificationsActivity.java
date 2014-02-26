package groupo.travellight.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.app.NotificationManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Brandon on 2/25/14.
 */
public class NotificationsActivity extends ActionBarActivity {
    public int notificationCount=0;
    public int layoutindex=0;
    TextView countDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notifications);
        countDisplay=(TextView)findViewById(R.id.countDisplays);
        countDisplay.setText(""+notificationCount);
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
                goToHelp();
                return true;
            case R.id.action_home:
                goToMain();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToHelp(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
    public void goToMain(){
        Intent intent = new Intent (this, MainScreen.class);
        startActivity(intent);
    }

    public void activateNotification(int id,String contentTitle, String contentText, String ticker){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.abc_ab_bottom_solid_dark_holo)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setTicker(ticker)
                        .setAutoCancel(true);
        Intent resultIntent = new Intent(this, HelpActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        //issue the notification:
        int mNotificationId =001;
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(id,mBuilder.build());


    }
    public void increaseCount(View view){
        notificationCount++;
        countDisplay.setText("" + notificationCount);
        if (notificationCount>2){
            activateNotification(01,"Count 3","Count has reached 3","Count Increased to 3!");
            addTextView("Count Increased to "+notificationCount+ "!");
        }

    }
    public void addTextView(String message){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutindex++;
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.container);
        TextView textView = new TextView(getBaseContext());
        textView.setText(message);
        rl.addView(textView,layoutindex,params);

    }


}
