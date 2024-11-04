package pelanggan;

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

import java.util.UUID;

import database.SQLiteHelper;
import model.ModelPelanggan;

public class PelangganAddActivity extends AppCompatActivity {

    EditText nama, email, hp;

    Button btnSimpan, btnBatal;

    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pelanggan_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new SQLiteHelper(PelangganAddActivity.this);

        nama = (EditText) findViewById(R.id.edPelAddNama);
        email = (EditText) findViewById(R.id.edPelAddEmail);
        hp = (EditText) findViewById(R.id.edPelAddHp);
        btnSimpan = (Button) findViewById(R.id.btnPelAddSimpan);
        btnBatal = (Button) findViewById(R.id.btnPelAddBatal);

        btnSimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ModelPelanggan mp = new ModelPelanggan();
                String UniqueID = UUID.randomUUID().toString();
                mp.setId(""+UniqueID);
                mp.setNama(nama.getText().toString());
                mp.setEmail(email.getText().toString());
                mp.setHp(hp.getText().toString());

                Toast.makeText(PelangganAddActivity.this, ""+mp.getId()+mp.getNama()+mp.getEmail()+mp.getHp(), Toast.LENGTH_SHORT).show();


                boolean cek = db.insertPelanggan(mp);

                if(cek){
                    Toast.makeText(PelangganAddActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PelangganAddActivity.this, PelangganActivity.class));
                    finish();
                }else{
                    Toast.makeText(PelangganAddActivity.this, "Data gagal Disimpan", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}