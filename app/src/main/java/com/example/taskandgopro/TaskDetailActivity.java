package com.example.taskandgopro;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.taskandgopro.models.Task;


public class TaskDetailActivity extends AppCompatActivity {

    EditText editNome;
    EditText editDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Task tarefaEnviada = (Task) getIntent().getSerializableExtra("tarefaEnviada");

        String nomeTarefa = tarefaEnviada.getName();
        String descricaoTarefa = tarefaEnviada.getDescription();
        Boolean estadoTarefa = tarefaEnviada.getWasDone();
        String tipoTarefa = tarefaEnviada.getType();

        this.editNome = findViewById(R.id.editNome);
        this.editDescricao = findViewById(R.id.editDescricao);

        this.editNome.setText(nomeTarefa);
        this.editDescricao.setText(descricaoTarefa);



    }
}