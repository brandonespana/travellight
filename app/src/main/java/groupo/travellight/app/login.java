package groupo.travellight.app;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.login, menu);
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

    public void goToMain(){
        Intent intent = new Intent (this, MainScreen.class);
        startActivity(intent);
    }
    public void goToHelp(){
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
    }
}
