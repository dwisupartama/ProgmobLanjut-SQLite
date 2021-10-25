package id.dwisupartama.appsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.dwisupartama.appsqllite.Helper.Database;

public class CreateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button buttonSimpan;
    EditText nim, nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        database = new Database(this);
        nim = findViewById(R.id.inputNIM);
        nama = findViewById(R.id.inputNama);
        alamat = findViewById(R.id.inputAlamat);
        buttonSimpan = findViewById(R.id.buttonSimpan);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("INSERT INTO tb_mahasiswa VALUES('"+nim.getText().toString()+"', '"+nama.getText().toString()+"', '"+alamat.getText().toString()+"')");
                Toast.makeText(CreateActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivity.mainActivity.RefreshList();
                finish();
            }
        });
    }
}