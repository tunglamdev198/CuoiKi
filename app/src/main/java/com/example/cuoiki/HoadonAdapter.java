package com.example.cuoiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HoadonAdapter extends ArrayAdapter<HoaDon> {
    private List<HoaDon> hoaDons;
    private int resource;
    private Context context;
    private LayoutInflater inflater;
    public HoadonAdapter(Context context, int resource, List<HoaDon> hoaDons) {
        super(context, resource, hoaDons);
        this.context = context;
        this.resource = resource;
        this.hoaDons = hoaDons;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(resource,parent,false);
            holder.txtSoXe = convertView.findViewById(R.id.txtSoXe);
            holder.txtQuangDuong = convertView.findViewById(R.id.txtQuangDuong);
            holder.txtTongTien = convertView.findViewById(R.id.txtTongTien);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        HoaDon hoaDon = hoaDons.get(position);
        holder.txtSoXe.setText(hoaDon.getSoXe());
        holder.txtQuangDuong.setText("Quãng đường: "+hoaDon.getQuangDuong()+ " km");
        holder.txtTongTien.setText(""+hoaDon.tongTien());
        return convertView;
    }

    public class ViewHolder{
        private TextView txtSoXe;
        private TextView txtQuangDuong;
        private TextView txtTongTien;
    }
}
