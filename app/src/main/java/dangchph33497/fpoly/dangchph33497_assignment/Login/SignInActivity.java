package dangchph33497.fpoly.dangchph33497_assignment.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import dangchph33497.fpoly.dangchph33497_assignment.Content.ManagementActivity;
import dangchph33497.fpoly.dangchph33497_assignment.Model.Account;
import dangchph33497.fpoly.dangchph33497_assignment.R;
import dangchph33497.fpoly.dangchph33497_assignment.ReadAndWrite.Xfile;

public class SignInActivity extends AppCompatActivity {
    CheckBox chkLuu;
    ArrayList<Account>list = new ArrayList<>();
    AppCompatEditText edtPassword,edtUsername;
    String u="",p="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtUsername  =findViewById(R.id.edtUsername);
        edtPassword =findViewById(R.id.edtPassword);
        chkLuu = findViewById(R.id.chkLuu);
        checkRemember();
        list = (ArrayList<Account>) Xfile.doc(SignInActivity.this,"taiKhoan.txt") ;
        Button btnSignIn = findViewById(R.id.btnSignIn);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u = edtUsername.getText().toString();
                p = edtPassword.getText().toString();
                if(TextUtils.isEmpty(u)||TextUtils.isEmpty(p)){
                    Toast.makeText(SignInActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }else {
                    if(!list.isEmpty()){
                        for (int i = 0; i < list.size(); i++) {
                            if(u.equals(list.get(i).getUsername()) && p.equals(list.get(i).getPassword())){
                                Intent intent1 = new Intent(SignInActivity.this, ManagementActivity.class);
                                Toast.makeText(SignInActivity.this, "Đăng nhập success", Toast.LENGTH_SHORT).show();
                                remember(chkLuu.isChecked());
                                startActivity(intent1);
                                return;
                            }
                        }
                        Toast.makeText(SignInActivity.this, "Đăng nhập fail", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SignInActivity.this, "Chưa đăng ký", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public void remember(Boolean chk){
        SharedPreferences s = getSharedPreferences("remember",MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putString("user",edtUsername.getText().toString());
        editor.putString("pass",edtPassword.getText().toString());
        editor.putBoolean("Checking",chk);
        editor.apply();
    }
    public void checkRemember(){
        SharedPreferences s = getSharedPreferences("remember",MODE_PRIVATE);
        u = s.getString("user","");
        p = s.getString("pass","");
        boolean chk1 = s.getBoolean("Checking",false);
        chkLuu.setChecked(chk1);
        if (chkLuu.isChecked()){
            edtUsername.setText(u);
            edtPassword.setText(p);
        }
    }
}