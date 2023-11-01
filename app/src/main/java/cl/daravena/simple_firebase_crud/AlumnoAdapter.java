package cl.daravena.simple_firebase_crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cl.daravena.simple_firebase_crud.model.Alumno;


public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.ViewHolder>{

    ArrayList<Alumno> dataset = new ArrayList<>();
    public AlumnoAdapter(ArrayList<Alumno> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_alumno, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Por implementar
        holder.getTxtIdentificadorAlumno().setText(dataset.get(position).getIdentificador());
        holder.getTxtNombreAlumno().setText(dataset.get(position).getNombre());
        holder.getTxtDescripcionAlumno().setText(dataset.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtIdentificadorAlumno;
        private TextView txtNombreAlumno;
        private TextView txtDescripcionAlumno;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdentificadorAlumno = itemView.findViewById(R.id.txtIdentificadorAlumno);
            txtNombreAlumno = itemView.findViewById(R.id.txtNombreAlumno);
            txtDescripcionAlumno = itemView.findViewById(R.id.txtDescripcionAlumno);
        }

        public TextView getTxtIdentificadorAlumno() {
            return txtIdentificadorAlumno;
        }

        public TextView getTxtNombreAlumno() {
            return txtNombreAlumno;
        }

        public TextView getTxtDescripcionAlumno() {
            return txtDescripcionAlumno;
        }
    }
}
