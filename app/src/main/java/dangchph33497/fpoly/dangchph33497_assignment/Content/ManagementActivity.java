package dangchph33497.fpoly.dangchph33497_assignment.Content;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dangchph33497.fpoly.dangchph33497_assignment.MainActivity;
import dangchph33497.fpoly.dangchph33497_assignment.R;

public class ManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        Button btnPhongBan = findViewById(R.id.btnPhongBan);
        Button btnNhanSu = findViewById(R.id.btnNhanSu);
        Button btnThoat =findViewById(R.id.btnThoat);
        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PhongBan = new Intent(ManagementActivity.this, PhongBanActivity.class);
                startActivity(PhongBan);
            }
        });
        btnNhanSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NhanSu = new Intent(ManagementActivity.this, NhanVienActivity.class);
                startActivity(NhanSu);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Thoat = new Intent(ManagementActivity.this, MainActivity.class);
                startActivity(Thoat);
            }
        });
    }
}