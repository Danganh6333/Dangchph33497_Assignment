package dangchph33497.fpoly.dangchph33497_assignment.Content;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Adapter.PhongBanSpinnerAdapter;
import dangchph33497.fpoly.dangchph33497_assignment.Model.NhanVien;
import dangchph33497.fpoly.dangchph33497_assignment.Model.PhongBan;
import dangchph33497.fpoly.dangchph33497_assignment.R;

public class AddUserActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edTenNguoiDung,edMaNguoiDung;
    Button btnThem,btnBoQua;
    String phongBan;
    PhongBanSpinnerAdapter phongBanSpinnerAdapter;
    private ArrayList<PhongBan> phongBans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        spinner = findViewById(R.id.Spinner);
        edTenNguoiDung = findViewById(R.id.edTenNguoiDung);
        edMaNguoiDung = findViewById(R.id.edMaNguoiDung);
        btnThem = findViewById(R.id.btnThem);
        btnBoQua = findViewById(R.id.btnBoQua);
        phongBans.add(new PhongBan("Nhân sự"));
        phongBans.add(new PhongBan("Hành chính"));
        phongBans.add(new PhongBan("Đào tạo"));
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edTenNguoiDung.getText().toString();
                int maNV = Integer.parseInt(edMaNguoiDung.getText().toString());
                //Gửi dữ liệu đi
                Intent  intent =  new Intent(AddUserActivity.this, NhanVienActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("maNV",maNV);
                bundle.putString("hoTen",hoTen);
                bundle.putString("phongBan",phongBan);
                intent.putExtras(bundle);
                //startActivity(intent);
                setResult(1,intent);
                finish();
            }
        });
        phongBanSpinnerAdapter = new PhongBanSpinnerAdapter(this,phongBans);
        spinner.setAdapter(phongBanSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                phongBan = phongBans.get(position).getTenPhongBan();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}