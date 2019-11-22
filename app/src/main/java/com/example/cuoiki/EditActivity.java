package com.example.cuoiki;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private EditText edtSoXe;
    private EditText edtQuangDuong;
    private EditText edtDonGia;
    private EditText edtKhuyenMai;
    private Button btnSua;
    private Button btnQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initVews();
    }

    private void initVews() {
        edtSoXe = findViewById(R.id.edtSoXe);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtQuangDuong = findViewById(R.id.edtquangDuong);
        edtKhuyenMai = findViewById(R.id.edtKhyenMai);
        btnQuayVe = findViewById(R.id.btnQuayVe);
        final Intent intent = getIntent();
        edtSoXe.setText(intent.getStringExtra("soxe"));
        edtDonGia.setText(intent.getStringExtra("dongia"));
        edtKhuyenMai.setText(""+intent.getIntExtra("khuyenmai",0));
        edtQuangDuong.setText(intent.getDoubleExtra("quangduong",0)+"");
        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSua = findViewById(R.id.btnSua);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("soxe",edtSoXe.getText().toString());
                intent1.putExtra("quangduong",edtQuangDuong.getText().toString());
                intent1.putExtra("dongia",edtDonGia.getText().toString());
                intent1.putExtra("khuyenmai",edtKhuyenMai.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }
}
