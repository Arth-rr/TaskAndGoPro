package com.example.taskandgopro;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

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

        //Botão guardar
        this.buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                //Criar os atributos da nova tarefa
                String nomeDaTarefa = editNome.getText().toString();
                String descricaoDaTarefa = editDescricao.getText().toString();

                Bundle result = new Bundle();

                //Passar os atributos para o bundle
                result.putString("nome", nomeDaTarefa);
                result.putString("descricao", descricaoDaTarefa);

                //Teste Bundle
                //Toast.makeText(v.getContext(), "Nome: " + result.getString("nome") + " Descrição: " + result.getString("descricao"), Toast.LENGTH_SHORT).show();

                //Passar o bundle para o fragment
                getParentFragmentManager().setFragmentResult("dados_nova_tarefa", result);

                dismiss();
            }
        });

        return v;

    }
}