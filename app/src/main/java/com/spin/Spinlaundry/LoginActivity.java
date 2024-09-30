package com.spin.Spinlaundry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.spin.laundry.R;

public class LoginActivity extends AppCompatActivity {

    String user = "saya";
    String pass = "nama";

    EditText Username,IDPassword;
    Button btnlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Username = (EditText)findViewById(R.id.IDUsername) ;
        IDPassword = (EditText)findViewById(R.id.IDPassword) ;
        btnlog = (Button)findViewById(R.id.btnlog);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Username.getText().toString();
                String password = IDPassword.getText().toString();

                // Periksa apakah username dan password sesuai
                if(username.equals(user) && password.equals(pass)) {
                    // Jika benar, arahkan ke halaman Dashboard
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Jika salah, tampilkan pesan dan tetap di halaman login
                    Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}