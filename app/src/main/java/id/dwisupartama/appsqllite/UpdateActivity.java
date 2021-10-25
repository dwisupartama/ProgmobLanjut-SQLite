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

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button buttonUpdate;
    EditText nim, nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = new Database(this);
        nim = findViewById(R.id.inputNIM);
        nama = findViewById(R.id.inputNama);
        alamat = findViewById(R.id.inputAlamat);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        SQLiteDatabase db = database.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM tb_mahasiswa WHERE nim = '"+getIntent().getStringExtra("nim")+"'", null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            nim.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE tb_mahasiswa SET nim = '"+nim.getText().toString()+"', nama = '"+nama.getText().toString()+"', alamat = '"+alamat.getText().toString()+"' WHERE nim = '"+getIntent().getStringExtra("nim")+"'");
                Toast.makeText(UpdateActivity.this, "Data Berhasil Diperbaharui", Toast.LENGTH_SHORT).show();
                MainActivity.mainActivity.RefreshList();
                finish();
            }
        });
    }
}