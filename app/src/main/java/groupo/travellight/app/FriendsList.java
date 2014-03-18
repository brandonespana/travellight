package groupo.travellight.app;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import groupo.travellight.app.R;

/**
 * Created by Brandon on 3/13/14.
 */

public class FriendsList extends ListFragment implements AddFriendDialog.AddFriendDialogListener {
    private ListView lv;
    private ArrayList<Friend> listOfFriends;            //new array of Friend objects
    private FriendAdapter adapter;
    private MenuInflater inflateer;
    private String filename;//to save the friends list
    private File file;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        filename="ListOfFriends.txt";
        setHasOptionsMenu(true);
    }

    @Override
    public void onStop(){
        super.onStop();
        saveListToFile(listOfFriends);
    }

    @Override
    public void onPause(){
        super.onPause();
        saveListToFile(listOfFriends);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.friends_options, menu);
        inflateer=inflater;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        inflateer.inflate(R.menu.friends_list_context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info;
        switch(item.getItemId()){
            case R.id.context_remove:
                info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                removeFriend(info.position);
                return true;
            case R.id.context_edit:
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                getFriendInfo(info.position);
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.actionAddfriend:
                popupDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle instancedState){
        listOfFriends=readListFromFile();
        View rootView = inflater.inflate(R.layout.friends_fragment,container,false);
        lv = (ListView) rootView.findViewById(android.R.id.list);
        adapter = new FriendAdapter(this.getActivity(),R.layout.individual_list_item_friends, listOfFriends);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        return rootView;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        l.showContextMenuForChild(v);
//        Toast.makeText(getActivity().getApplicationContext(),
//                "Clicked ListItem Number " + position, Toast.LENGTH_LONG)
//                .show();
    }

    public void addFriend(String name, String email){
        Friend newFriend = new Friend(name, email, 1);//*
        listOfFriends.add(newFriend);//*
        saveListToFile(listOfFriends);//*
        adapter.notifyDataSetChanged();
    }

    public void removeFriend(int position){//used to be removeFriend(String, String)
        listOfFriends.remove(position);
        saveListToFile(listOfFriends);
        adapter.notifyDataSetChanged();
    }
    public void getFriendInfo(int position){
        String currentEmail =listOfFriends.get(position).getEmail();
        String currentName = listOfFriends.get(position).getName();
        popupEditDialog(currentName, currentEmail,position);
    }
    public void editFriend(String name, String email, int position){
        listOfFriends.get(position).setName(name);
        listOfFriends.get(position).setEmail(email);
        adapter.notifyDataSetChanged();

    }

    public void popupDialog(){
        AddFriendDialog dialog = new AddFriendDialog();
        dialog.setTargetFragment(this, 0);
        dialog.show(this.getFragmentManager(), "add popup");
    }

    public void popupEditDialog(String name, String email, int position){
        AddFriendDialog dialog = new AddFriendDialog(name, email,position, true);
        dialog.setTargetFragment(this,0);
        dialog.show(this.getFragmentManager(), "edit popup");
    }

    private void saveListToFile(ArrayList<Friend> listOfFriends) {//used to take ArrayList<String> listOfStuffas parameter type
        try {

            file = new File(getActivity().getFilesDir(), filename);
            FileOutputStream fos = new FileOutputStream (file);
            ObjectOutputStream os = new ObjectOutputStream ( fos );
            os.writeObject ( listOfFriends );
            fos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Friend> readListFromFile(){//used to return ArrayList<String>
        ArrayList<Friend> listOfFriendObjs = new ArrayList<Friend>();

        try {
            FileInputStream fis =new FileInputStream(getActivity().getFilesDir()+File.separator+filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            listOfFriendObjs = (ArrayList<Friend>) ois.readObject();
            fis.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();System.out.println("Error?");
        }
        return listOfFriendObjs;

    }

    //AddFriendDialogListener interface implementation:
    @Override
    public void clickedPositive(String name, String email,int position, boolean editing){
        if (editing) editFriend(name,email,position);
        else addFriend(name, email);
    }

}