package com.example.cuoiki;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtSearch;
    private ListView lvHoaDon;
    private FloatingActionButton btnAdd;

    private MyDatabase myDB;
    private int index;
    private HoadonAdapter adapter;

    List<HoaDon> hoaDons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initViews() {
        edtSearch = findViewById(R.id.edtSearch);
        lvHoaDon = findViewById(R.id.lvHoaDon);
        btnAdd = findViewById(R.id.btnAdd);
        myDB = new MyDatabase(this);
        hoaDons = generrate();
        adapter = new HoadonAdapter(this,R.layout.item_hoadon,hoaDons);
        lvHoaDon.setAdapter(adapter);
        registerForContextMenu(lvHoaDon);

    }

    private List<HoaDon> generrate(){
        if(myDB.getAll().size()==0) {
            HoaDon hoaDon1 = new HoaDon("29D2-283.34",14.3,"10000",10);
            HoaDon hoaDon2 = new HoaDon("29M3-857-654",9.6,"20000",20);
            HoaDon hoaDon3 = new HoaDon("29T2-283.34",6.5,"15000",10);
            HoaDon hoaDon4 = new HoaDon("29T4-283.34",10,"18000",20);
            HoaDon hoaDon5= new HoaDon("30K1-129.84",15,"15000",10);
            HoaDon hoaDon6 = new HoaDon("30K1-129-84",9.2,"12000",15);
            myDB.insert(hoaDon1);
            myDB.insert(hoaDon2);
            myDB.insert(hoaDon3);
            myDB.insert(hoaDon4);
            myDB.insert(hoaDon5);
            myDB.insert(hoaDon6);
            List<HoaDon> hoaDons = myDB.getAll();
            return hoaDons;
        }
        else {
            return myDB.getAll();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.lvHoaDon) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        index = info.position;
        switch (item.getItemId()) {
            case R.id.mnuSua:
                HoaDon hoaDon = hoaDons.get(index);
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                intent.putExtra("soxe",hoaDon.getSoXe());
                intent.putExtra("quangduong",hoaDon.getQuangDuong());
                intent.putExtra("dongia",hoaDon.getDonGia());
                intent.putExtra("khuyenmai",hoaDon.getPhanTram());
                startActivityForResult(intent,1000);
                break;

            case R.id.mnuXoa:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xóa")
                        .setMessage("Bạn có muốn xóa không?")
                        .setCancelable(true);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Đóng Dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        myDB.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myDB.closeDB();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1000:
                if(resultCode == Activity.RESULT_OK){
                   String soXe = data.getStringExtra("soxe");
                    String donGia = data.getStringExtra("dongia");
                    int khuyenMai = data.getIntExtra("khuyenmai",0);
                    double quangDuong = data.getDoubleExtra("quangduong");
                }
        }
    }
}
