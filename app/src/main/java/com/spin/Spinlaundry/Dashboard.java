package com.spin.Spinlaundry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.spin.laundry.R;

public class Dashboard extends AppCompatActivity {

    CardView Laundry, Layanan, Pelanggan, Promo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Laundry = (CardView) findViewById(R.id.Laundry);
        Layanan = (CardView) findViewById(R.id.Layanan);
        Pelanggan = (CardView) findViewById(R.id.Pelanggan);
        Promo = (CardView) findViewById(R.id.Promo);

        Laundry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Dashboard.this, LaundryActivity.class);
                startActivity(intent);
            }
        });

        Pelanggan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Dashboard.this, PelangganActivity.class);
                startActivity(intent);
            }
        });

        Layanan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Dashboard.this, LayananActivity.class);
                startActivity(intent);
            }
        });


        Promo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Dashboard.this, PromoActivity.class);
                startActivity(intent);
            }
        });
    }
}