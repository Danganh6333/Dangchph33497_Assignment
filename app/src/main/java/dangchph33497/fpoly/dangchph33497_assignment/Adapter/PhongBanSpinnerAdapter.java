package dangchph33497.fpoly.dangchph33497_assignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Model.PhongBan;
import dangchph33497.fpoly.dangchph33497_assignment.R;

public class PhongBanSpinnerAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<PhongBan> phongBans;

    public PhongBanSpinnerAdapter(Context context, ArrayList<PhongBan> phongBans) {
        this.context = context;
        this.phongBans = phongBans;
    }

    @Override
    public int getCount() {
        return phongBans.size();
    }

    @Override
    public Object getItem(int position) {
        return phongBans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_nhan_vien_spinner,parent,false);
        TextView tvPhongBan = convertView.findViewById(R.id.tvPhongBan);
        tvPhongBan.setText(phongBans.get(position).getTenPhongBan());
        return convertView;
    }
}
