package pelanggan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spin.laundry.R;

import java.util.ArrayList;
import java.util.List;

import AdapterPelanggan.AdapterPelanggan;
import database.SQLiteHelper;
import model.ModelPelanggan;

public class PelangganActivity extends AppCompatActivity {

    SQLiteHelper db;
    Button btnPelAdd;
    RecyclerView rvPelanggan;
    AdapterPelanggan adapterPelanggan;
    ArrayList<ModelPelanggan> list;
    ProgressDialog progressDialog;
    AlphaAnimation btnAnimasi = new AlphaAnimation(1F, 0.5F);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pelanggan);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setView();
        eventHandling();
        getData();



        btnPelAdd = (Button) findViewById(R.id.btnPelAdd);

        btnPelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PelangganActivity.this,PelangganAddActivity.class));
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            v.startAnimation(btnAnimasi);
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            ModelPelanggan mp = list.get(position);
        }
    };

    private void getData(){
        list.clear();
        showMsg();
        progressDialog.dismiss();

        try {
            List<ModelPelanggan> p = db.getPelanggan();
            if (p.size() > 0){
                for (ModelPelanggan pel : p){
                    ModelPelanggan mp = new ModelPelanggan();
                    mp.setId(pel.getId());
                    mp.setNama(pel.getNama());
                    mp.setEmail(pel.getEmail());
                    mp.setHp(pel.getHp());
                    list.add(mp);
                }

                adapterPelanggan = new AdapterPelanggan(this, list);
                adapterPelanggan.notifyDataSetChanged();
                rvPelanggan.setAdapter(adapterPelanggan);
                adapterPelanggan.setOnItemClickListener(onClickListener);

            }else{
                Toast.makeText(this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    private void eventHandling(){
        btnPelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PelangganActivity.this, PelangganAddActivity.class));
            }
        });
    }


    private void setView(){
        db = new SQLiteHelper(this);
        progressDialog = new ProgressDialog(this);
        btnPelAdd = findViewById(R.id.btnPelAdd);
        rvPelanggan = findViewById(R.id.rvPelanggan);
        list = new ArrayList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPelanggan.setHasFixedSize(true);
        rvPelanggan.setLayoutManager(llm);

    }

    private void showMsg(){
        progressDialog.setTitle("Informasi");
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        getData();
    }
}