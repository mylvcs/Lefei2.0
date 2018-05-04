//package com.example.wangmengyun.MongoLab;
//
///**
// * Created by wangmengyun on 2018/4/22.
// */
//
//import android.app.ListActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.wangmengyun.activity.DetailActivity;
//import com.example.wangmengyun.lefei.R;
//
//import java.util.ArrayList;
//import java.util.concurrent.ExecutionException;
//
//
//public class ViewContactsActivity extends ListActivity {
//    ArrayList<MyFlight> returnValues = new ArrayList<MyFlight>();
//    ArrayList<String> listItems = new ArrayList<String>();
//    String valueTOUpdate_id;
//    String valueTOUpdate_fname;
//    String valueTOUpdate_lname;
//    String valueTOUpdate_phone;
//    String valueTOUpdate_email;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.list_view_contacts);
//
//        //Get your cloud contacts
//        GetContactsAsyncTask task = new GetContactsAsyncTask();
//        try {
//            returnValues = task.execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        for(MyFlight x: returnValues){
//
//            listItems.add(x.get_id());
//        }
//
//        setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, listItems));
//
//
//    }
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        String selectedValue = (String) getListAdapter().getItem(position);
//        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
//        selectedContact(selectedValue);
//
//        Bundle dataBundle = new Bundle();
//        dataBundle.putString("_id", valueTOUpdate_id);
//        dataBundle.putString("first_name", valueTOUpdate_fname);
//        dataBundle.putString("last_name", valueTOUpdate_lname);
//        dataBundle.putString("phone", valueTOUpdate_phone);
//        dataBundle.putString("email", valueTOUpdate_email);
//        Intent moreDetailsIntent = new Intent(this,DetailActivity.class);
//        moreDetailsIntent.putExtras(dataBundle);
//        startActivity(moreDetailsIntent);
//    }
//
//    /*
//     * Retrieves the full details of a selected contact.
//     * The details are then passed onto the Update Contacts Record.
//     *
//     * This is a quick way for demo purposes.
//     * You should consider storing this data in a database, shared preferences or text file
//     */
//    public void selectedContact(String selectedValue){
//        for(MyFlight x: returnValues){
//            if(selectedValue.contains(x.get_id())){
//                valueTOUpdate_id = x.get_id();
//                valueTOUpdate_fname = x.getDeparture();
//                valueTOUpdate_lname = x.getArrival();
//                valueTOUpdate_phone = x.getTicket_price();
//                valueTOUpdate_email = x.getAirline();
//            }
//        }
//
//    }
//
//
//}
