package dangchph33497.fpoly.dangchph33497_assignment.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Content.ManagementActivity;
import dangchph33497.fpoly.dangchph33497_assignment.Model.Account;
import dangchph33497.fpoly.dangchph33497_assignment.R;
import dangchph33497.fpoly.dangchph33497_assignment.ReadAndWrite.Xfile;

public class SignUpActivity extends AppCompatActivity {
    private Intent intent;
    ArrayList<Account> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AppCompatEditText edtUsername = findViewById(R.id.edtUsername);
        AppCompatEditText edtPassword = findViewById(R.id.edtPassword);
        AppCompatEditText edtConfirm = findViewById(R.id.edtConfirm);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        Button btnBack = findViewById(R.id.btnBack);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String confirm = edtConfirm.getText().toString();
                if(TextUtils.isEmpty(username)|TextUtils.isEmpty(password)|TextUtils.isEmpty(confirm)){
                    Toast.makeText(SignUpActivity.this, "Không để rỗng các trường dữ liệu", Toast.LENGTH_SHORT).show();
                }else {
                    if(password.equals(confirm)){
                        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                        list.add(new Account(username,password));
                        list.add(new Account("b","c"));
                        list.add(new Account("c","d"));
                        Xfile.ghi(SignUpActivity.this,"taiKhoan.txt",list);
                        Toast.makeText(SignUpActivity.this, "Đăng nhập success", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignUpActivity.this, "Pass phải trùng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}