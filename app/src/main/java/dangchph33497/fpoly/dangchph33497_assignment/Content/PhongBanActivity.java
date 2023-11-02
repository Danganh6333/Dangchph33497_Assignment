package dangchph33497.fpoly.dangchph33497_assignment.Content;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Adapter.PhongBanAdapter;
import dangchph33497.fpoly.dangchph33497_assignment.Model.PhongBan;
import dangchph33497.fpoly.dangchph33497_assignment.R;

public class PhongBanActivity extends AppCompatActivity {
    ArrayList<PhongBan> phongBans = new ArrayList<>();
    ListView lvPhongBan;
    PhongBanAdapter phongBanAdapter;
    Toolbar tbPhongBan;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        lvPhongBan = findViewById(R.id.lvPhongBan);
        tbPhongBan = findViewById(R.id.tbPhongBan);
        setSupportActionBar(tbPhongBan);
        getSupportActionBar().setTitle("Phòng ban");
        getSupportActionBar().getThemedContext();
        tbPhongBan.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbPhongBan.setNavigationIcon(R.drawable.twotone_arrow_back_24);
        phongBans.add(new PhongBan("Nhân sự"));
        phongBans.add(new PhongBan("Hành chính"));
        phongBans.add(new PhongBan("Đào tạo"));
        phongBanAdapter = new PhongBanAdapter(this,phongBans);
        tbPhongBan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getOnBackPressedDispatcher().onBackPressed();
            }
        });
        lvPhongBan.setAdapter(phongBanAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phong_ban,menu);
        MenuItem menuItem = menu.findItem(R.id.timKiemPhongBan);
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
                phongBanAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}