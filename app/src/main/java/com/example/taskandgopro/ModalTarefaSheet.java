package com.example.taskandgopro;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ModalTarefaSheet.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ModalTarefaSheet extends BottomSheetDialogFragment {

    Button buttonCancelar;
    Button buttonGuardar;

    EditText editNome;
    EditText editDescricao;

    Chip chipEstadoTarefa;

    Spinner spinnerTipoTarefa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_modal_tarefa, container, false);

        this.buttonCancelar = v.findViewById(R.id.buttonCancelar);

        this.buttonGuardar = v.findViewById(R.id.buttonGuardar);

        //Event Listeners
        //Botão cancelar
        this.buttonCancelar.setOnClickListener(view -> {
            dismiss();
        });

        this.editNome = v.findViewById(R.id.editNome);

        this.editDescricao = v.findViewById(R.id.editDescricao);

        //inicializar a variavel chip
        this.chipEstadoTarefa = v.findViewById(R.id.chipEstadoTarefa);


        //Botão guardar
        this.buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                //Criar os atributos da nova tarefa
                String nomeDaTarefa = editNome.getText().toString();
                String descricaoDaTarefa = editDescricao.getText().toString();
                Boolean estadoDaTarefa = chipEstadoTarefa.isChecked();
                String tipoDaTarefa = spinnerTipoTarefa.getSelectedItem().toString();

                //Toast.makeText(v.getContext(), "" + estadoDaTarefa + "||" + chipEstadoTarefa.isChecked(), Toast.LENGTH_SHORT).show();

                Bundle result = new Bundle();

                //Passar os atributos para o bundle
                result.putString("nome", nomeDaTarefa);
                result.putString("descricao", descricaoDaTarefa);
                result.putBoolean("wasDone ", estadoDaTarefa);
                result.putString("tipo", tipoDaTarefa);

                //Teste Bundle
                //Toast.makeText(v.getContext(), "Nome: " + result.getString("nome") + " Descrição: " + result.getString("descricao"), Toast.LENGTH_SHORT).show();

                //Passar o bundle para o fragment
                getParentFragmentManager().setFragmentResult("dados_nova_tarefa", result);

                dismiss();
            }
        });


        //apanhar o valor de chip de inicio
        Boolean valorInicialChip = this.chipEstadoTarefa.isChecked();

        //aplicar o listener
        this.chipEstadoTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Boolean novoValorChip = chipEstadoTarefa.isChecked();

                alternarDisplayChip();

                //Toast.makeText(v.getContext(), "Tarefa finalizada: " + novoValorChip, Toast.LENGTH_SHORT).show();


            }
        });

        this.alternarDisplayChip();

        this.spinnerTipoTarefa = v.findViewById(R.id.spinnerTipoTarefa);

        //Criar lista de tipos de tarefas para o spinner
        String[] listaTipoTarefa = {"Limpar", "Estudar", "Divertir"};

        ArrayAdapter<String> listaTarefasAdapter = new ArrayAdapter<String>(
                v.getContext(),
                android.R.layout.simple_spinner_item,
                listaTipoTarefa
        );

        listaTarefasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTipoTarefa.setAdapter(listaTarefasAdapter);

        /*
        spinnerTipoTarefa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tipoTarefaSelecionada = parent.getItemAtPosition(position).toString();

                Toast.makeText(v.getContext(),
                        "Tipo de tarefa: " + position + "|" + tipoTarefaSelecionada,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */



        return v;
    }

    private void alternarDisplayChip() {

        Boolean valorAtualChip= this.chipEstadoTarefa.isChecked();

        if (valorAtualChip) {

            String valorTexto = "Finalizada";

            this.chipEstadoTarefa.setText(valorTexto);

        } else {

            String valorTexto = "Por Fazer";

            this.chipEstadoTarefa.setText(valorTexto);

        }
    }

}