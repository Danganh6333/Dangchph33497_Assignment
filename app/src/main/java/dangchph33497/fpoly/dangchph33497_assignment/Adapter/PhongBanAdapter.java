package dangchph33497.fpoly.dangchph33497_assignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Model.PhongBan;
import dangchph33497.fpoly.dangchph33497_assignment.R;

public class PhongBanAdapter extends BaseAdapter implements Filterable {
    private final Context context;
    private ArrayList<PhongBan>phongBans;
    private ArrayList<PhongBan> phongBanArrayList;

    public PhongBanAdapter(Context context, ArrayList<PhongBan> phongBans) {
        this.context = context;
        this.phongBans = phongBans;
        this.phongBanArrayList = phongBans;
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
        convertView = inflater.inflate(R.layout.item_phong_ban,parent,false);
        TextView txtPhongBan = convertView.findViewById(R.id.txtPhongBan);
        txtPhongBan.setText(phongBans.get(position).getTenPhongBan());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if(s.isEmpty()){
                    phongBans=phongBanArrayList ;
                }else {
                    ArrayList<PhongBan> phongBanList = new ArrayList<>();
                    for(PhongBan pb : phongBanArrayList){
                        if(pb.getTenPhongBan().toLowerCase().contains(s.toLowerCase())){
                            phongBanList.add(pb);
                        }
                    }
                    phongBans = phongBanList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = phongBanArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                phongBanArrayList=(ArrayList<PhongBan>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
