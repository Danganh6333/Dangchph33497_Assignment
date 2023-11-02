package dangchph33497.fpoly.dangchph33497_assignment.Content;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;


import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Adapter.NhanVienAdapter;
import dangchph33497.fpoly.dangchph33497_assignment.Model.NhanVien;
import dangchph33497.fpoly.dangchph33497_assignment.R;
import dangchph33497.fpoly.dangchph33497_assignment.ReadAndWrite.Xfile;

public class NhanVienActivity extends AppCompatActivity {
    private ArrayList<NhanVien>nhanViens;
    ListView lvNhanVien;
    NhanVienAdapter nhanVienAdapter;
    Toolbar tbNhanVien;
    SearchView searchView;
    Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        nhanViens = new ArrayList<>();
        tbNhanVien = findViewById(R.id.tbNhanVien);
        btnThem = findViewById(R.id.btnThem);
        setSupportActionBar(tbNhanVien);
        getSupportActionBar().setTitle("Nhân viên");
        getSupportActionBar().getThemedContext();
        tbNhanVien.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbNhanVien.setNavigationIcon(R.drawable.twotone_arrow_back_24);
        lvNhanVien = findViewById(R.id.lvNhanVien);
        nhanViens.add(new NhanVien(1,"Nguyễn Văn B","Hành chính"));
        nhanViens.add(new NhanVien(2,"Nguyễn Văn C","Nhân sự"));
        nhanViens.add(new NhanVien(3,"Nguyễn Văn D","Nhân sự"));
        nhanViens.add(new NhanVien(4,"Nguyễn Văn E","Đào tạo"));
        if(nhanViens!=null){
            nhanViens = (ArrayList<NhanVien>) Xfile.doc(this,"nhanVien.txt");
        }
        nhanVienAdapter = new NhanVienAdapter(this,nhanViens);
        lvNhanVien.setAdapter(nhanVienAdapter);
        tbNhanVien.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode()==1){
                    Intent intent = o.getData();
                    if(intent!=null){
                        Bundle bundle = intent.getExtras();
                        int maNV = bundle.getInt("maNV");
                        String hoTen = bundle.getString("hoTen");
                        String phongBan = bundle.getString("phongBan");
                        nhanViens.add(new NhanVien(maNV,hoTen,phongBan));
                        Xfile.ghi(getApplicationContext(),"nhanVien.txt",nhanViens);
                        nhanVienAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVienActivity.this,AddUserActivity.class);
                launcher.launch(intent);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nhan_vien,menu);
        MenuItem menuItem = menu.findItem(R.id.timKiemNhanVien);
        searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("Tìm ở đây");
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageResource(R.drawable.twotone_search_24);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nhanVienAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

}