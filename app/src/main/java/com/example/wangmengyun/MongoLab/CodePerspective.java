package com.example.wangmengyun.MongoLab;

/**
 * Created by wangmengyun on 2018/4/22.
 */

        import java.net.UnknownHostException;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;

        import com.example.wangmengyun.lefei.R;

public class CodePerspective extends Activity {
    EditText editText_last_name;
    EditText editText_phone;
    EditText editText_email;
    EditText editText_fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perspective);

        editText_fname = (EditText) findViewById(R.id.editText_fname);
        editText_last_name = (EditText) findViewById(R.id.editText_last_name);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_phone = (EditText) findViewById(R.id.editText_phone);

    }

    public void saveContact(View v) throws UnknownHostException {

        MyFlight flight = new MyFlight();
        flight._id = editText_fname.getText().toString();
        flight.departure = editText_last_name.getText().toString();
        flight.arrival = editText_email.getText().toString();
        flight.ticket_price = editText_phone.getText().toString();

        SaveAsyncTask tsk = new SaveAsyncTask();
        tsk.execute(flight);

        Intent i = new Intent(this, ViewContactsActivity.class);
        startActivity(i);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
