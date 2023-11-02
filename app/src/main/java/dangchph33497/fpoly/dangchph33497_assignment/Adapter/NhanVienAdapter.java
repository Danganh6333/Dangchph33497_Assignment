package dangchph33497.fpoly.dangchph33497_assignment.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Content.PhongBanActivity;
import dangchph33497.fpoly.dangchph33497_assignment.Model.NhanVien;
import dangchph33497.fpoly.dangchph33497_assignment.Model.PhongBan;
import dangchph33497.fpoly.dangchph33497_assignment.R;
import dangchph33497.fpoly.dangchph33497_assignment.ReadAndWrite.Xfile;

public class NhanVienAdapter extends BaseAdapter implements Filterable {
    private final Context context;
    private  ArrayList<NhanVien> nhanViens;
    private  ArrayList<NhanVien>nhanVienArrayList;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> nhanViens) {
        this.context = context;
        this.nhanViens = nhanViens;
        this.nhanVienArrayList=nhanViens;
    }

    @Override
    public int getCount() {
        return nhanViens.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanViens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_nhan_vien,parent,false);
        TextView tvMaNV = convertView.findViewById(R.id.tvMaNV);
        TextView tvHoTen = convertView.findViewById(R.id.tvHoTen);
        TextView tvPhongBan = convertView.findViewById(R.id.tvPhongBan);
        ImageView imgSua = convertView.findViewById(R.id.imgSua);
        ImageView imgXoa = convertView.findViewById(R.id.imgXoa);
        tvMaNV.setText("Mã NV: NV00"+nhanViens.get(position).getMaNV());
        tvHoTen.setText("Họ tên: "+nhanViens.get(position).getHoTen());
        tvPhongBan.setText("Phòng ban: "+nhanViens.get(position).getPhongBan());
        NhanVien nhanVien = nhanViens.get(position);
        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(nhanVien);
            }
        });
        imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanViens.remove(position);
                Xfile.ghi(context,"nhanVien.txt",nhanViens);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if(s.isEmpty()){
                    nhanViens =nhanVienArrayList;
                }else {
                    ArrayList<NhanVien> vienArrayList = new ArrayList<>();
                    for(NhanVien st : nhanVienArrayList){
                        if(st.getHoTen().toLowerCase().contains(s.toLowerCase())){
                            vienArrayList.add(st);
                        }
                    }
                    nhanViens = vienArrayList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = nhanVienArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                nhanVienArrayList = (ArrayList<NhanVien>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    public void openDialog(NhanVien nhanVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhan_vien_update,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edHoTen = view.findViewById(R.id.edHoTen);
        EditText edPhongBan = view.findViewById(R.id.edPhongBan);
        Button btnSaveNV = view.findViewById(R.id.btnSaveNV);
        Button btnCancelNV = view.findViewById(R.id.btnCancelNV);
        edHoTen.setText(nhanVien.getHoTen());
        edPhongBan.setText(nhanVien.getPhongBan());
        btnCancelNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVien.setHoTen(edHoTen.getText().toString());
                nhanVien.setPhongBan(edPhongBan.getText().toString());
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                Xfile.ghi(context,"nhanVien.txt",nhanViens);
                dialog.dismiss();
            }
        });
    }
}
