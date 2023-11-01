package cl.daravena.simple_firebase_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cl.daravena.simple_firebase_crud.model.Alumno;

public class MainActivity extends AppCompatActivity {

    private Button btnForm;
    private RecyclerView rvAlumnos;
    private AlumnoAdapter alumnoAdapter;
    private ArrayList<Alumno> dataset = new ArrayList<>();
    private FirebaseDatabase db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        alumnoAdapter = new AlumnoAdapter(dataset);

        btnForm = findViewById(R.id.btnForm);
        btnForm.setOnClickListener(view -> {
            Intent i = new Intent(this, FormActivity.class);
            startActivity(i);
        });

        db = FirebaseDatabase.getInstance();
        db.getReference("Alumnos/" + auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataset.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    dataset.add(d.getValue(Alumno.class));
                }

                alumnoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase Error", "Error actualizando registros.", error.toException());
            }
        });

        rvAlumnos = findViewById(R.id.rvAlumnos);
        rvAlumnos.setLayoutManager(new LinearLayoutManager(this));
        rvAlumnos.setAdapter(alumnoAdapter);
    }
}