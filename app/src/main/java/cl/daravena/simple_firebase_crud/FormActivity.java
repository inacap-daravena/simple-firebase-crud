package cl.daravena.simple_firebase_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cl.daravena.simple_firebase_crud.model.Alumno;

public class FormActivity extends AppCompatActivity {

    EditText etIdAlumno, etNombreAlumno, etDescipcionAlumno;
    Button btnCancel, btnDelete, btnSave;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        auth = FirebaseAuth.getInstance();

        etIdAlumno = findViewById(R.id.etIdAlumno);
        etNombreAlumno = findViewById(R.id.etNombreAlumno);
        etDescipcionAlumno = findViewById(R.id.etDescipcionAlumno);

        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(view -> saveAlumno());
        btnCancel.setOnClickListener(view -> finish());
        btnDelete.setOnClickListener(view -> deleteAlumno());
    }

    private void saveAlumno() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        Alumno aux = createAlumno();
        String id = !aux.getIdentificador().isEmpty() ? aux.getIdentificador() : "0";
        DatabaseReference ref = db.getReference("Alumnos/"+ auth.getUid() + "/" + id);

        ref.setValue(aux);
    }

    private void deleteAlumno() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String id = !etIdAlumno.getText().toString().isEmpty() ? etIdAlumno.getText().toString() : "0";
        DatabaseReference ref = db.getReference("Alumnos/"+ auth.getUid() + "/" + id);

        ref.setValue(null);
    }

    private Alumno createAlumno(){
        String id = etIdAlumno.getText().toString();
        String nombre = etNombreAlumno.getText().toString();
        String descripcion = etDescipcionAlumno.getText().toString();

        return new Alumno(id, nombre, descripcion);
    }
}