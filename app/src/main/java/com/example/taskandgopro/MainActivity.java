package com.example.taskandgopro;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.taskandgopro.models.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Criar uma variavel de intancia para controlar tarefas
    private ArrayList<Task> tasksList;
    //Criar TextView
    private TextView textViewTarefas;
    private TextView textViewTarefasRealizadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //criar lista de tarefas
        this.tasksList = new ArrayList<>();
        //Chamar metodo para criar lista de tarefas
        this.buildTasks();

        //Linkar o textview com o xml
        this.textViewTarefas = findViewById(R.id.textViewTarefas);
        this.textViewTarefas.setText("" + this.tasksList.size());

        int numWastDone = this.countDoneTasks();
        this.textViewTarefasRealizadas = findViewById(R.id.textViewTarefasRealizadas);
        this.textViewTarefasRealizadas.setText("" + numWastDone);

        /*Toast.makeText(
            this,
            "Neste momento tenho na lista de tarefas: " + tasksList.size() + " tarefas",
            Toast.LENGTH_SHORT).show();
         */
    }

    /**
     * Metodo para construir a lista de tarefas iniciais
     */
    private void buildTasks(){
        //Criar tarefa t1
        //Instaciar a classe task
        Task t1 = new Task("Lavar a Louça", false, "30000 pratos pra lavar");
        //Criar tarefa t2
        Task t2 = new Task("Moggar o Fabio", true, "Bro nao sabe tar");
        //Criar tarefa t3
        Task t3 = new Task("Tarefa 3", false, "Faz a taré fa 3 da tarefa 3");

        //Adicionar as tarefas na lista
        tasksList.add(t1);
        tasksList.add(t2);
        tasksList.add(t3);

    }

    /**
     * Metodo para calcular quantas tarefas estão realizadas
     */
    private int countDoneTasks(){
        //Criar variavel de contador
        int count = 0;
        for(Task taskTemp : this.tasksList){
            //Verificar se a tarefa realizada == true
            if(taskTemp.getWasDone() == true){
                count++;

            }
        }
        return count;
    }



}