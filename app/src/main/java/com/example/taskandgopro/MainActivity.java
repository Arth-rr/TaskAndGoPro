package com.example.taskandgopro;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.taskandgopro.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Criar variavel de classe
    public static final int STRING_TEST_LEGHT = 0;

    //Criar uma variavel de intancia para controlar tarefas
    private ArrayList<Task> tasksList;
    //Criar TextView
    private TextView textViewTarefas;
    private TextView textViewTarefasRealizadas;
    //Criar floating button
    private FloatingActionButton buttonAddTask;



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
        //Linkar o floating button com o xml
        this.buttonAddTask = findViewById(R.id.buttonAddTask);

        this.buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buildTasks();
            }
        });

        //Linkar o textview com o xml
        this.textViewTarefas = findViewById(R.id.textViewTarefas);
        this.updTextTasksNum();

        //Linkar o textview com o xml
        this.textViewTarefasRealizadas = findViewById(R.id.textViewTarefasRealizadas);
        this.updTextDoneTasksNum();

        /*Toast.makeText(
            this,
            "Neste momento tenho na lista de tarefas: " + tasksList.size() + " tarefas",
            Toast.LENGTH_SHORT).show();
         */
    }


    /**
     * Metodo para Adicionar e cancelar tarefas
     */
    private void buildTasks(){

        ModalTarefaSheet modalTarefaSheet = new ModalTarefaSheet();

        modalTarefaSheet.show(getSupportFragmentManager(), "ModalTarefaSheet");

        getSupportFragmentManager()
                .setFragmentResultListener(
                        "dados_nova_tarefa",
                        MainActivity.this,
                        (requestKey, result) -> {

                            if (result.getString("nome").length() > STRING_TEST_LEGHT && result.getString("descricao").length() > STRING_TEST_LEGHT) {
                                String nomeDaTarefa = result.getString("nome");
                                String descricaoDaTarefa = result.getString("descricao");
                                Boolean estadoDaTarefa = result.getBoolean("wasDone");
                                String tipoDaTarefa = result.getString("tipo");

                                //Criar nova tarefa
                                Task novaTask = new Task(nomeDaTarefa, estadoDaTarefa, descricaoDaTarefa, tipoDaTarefa);

                                //Adicionar nova tarefa na lista de tarefas
                                tasksList.add(novaTask);

                                //Atualizar xml tarefas
                                updTextTasksNum();

                                //Atualizar xml tarefas realizadas
                                updTextDoneTasksNum();

                            }
                        });
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

    /**
     * Metodo para atualizar tarefas
     */
    protected void updTextTasksNum() {
        int numTarefas = this.tasksList.size();

        this.textViewTarefas.setText("" + numTarefas);
    }

    protected void updTextDoneTasksNum() {
        int numWastDone = this.countDoneTasks();

        this.textViewTarefasRealizadas.setText("" + numWastDone);
    }
}