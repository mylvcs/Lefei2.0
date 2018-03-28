//package com.example.wangmengyun.activity;
//
///**
// * Created by wangmengyun on 2018/3/21.
// */
//
//        import android.app.Activity;
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.ArrayAdapter;
//        import android.widget.AutoCompleteTextView;
//        import android.widget.Button;
//        import android.widget.MultiAutoCompleteTextView;
//
//        import com.example.wangmengyun.lefei.R;
//
//public class ZidongtishiActivity extends Activity {
//
//
//    private AutoCompleteTextView acTextView;
//    private Button button;
//
//    private String[] arr = {"Shanghai", "Beijing", "Chengdu", "Hangzhou", "Guangzhou"};
//    // private String[] brr = {"Los Angeles", "New York", "Seattle", "Dallas", "Boston", "Washington"};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_zidongtishi);
//
//        acTextView = findViewById(R.id.acTextView);
//        ArrayAdapter<String> arrAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr);
//        acTextView.setAdapter(arrAdapt);
//
//
//        button = findViewById(R.id.mButton_queding);
//
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(ZidongtishiActivity.this, SearchFlightActivity.class);
//                intent.putExtra(Intent.EXTRA_TEXT,acTextView.getText().toString());
//
//                startActivity(intent);
//            }
//        });
//
//    }
//}
//
