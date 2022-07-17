package com.example.baseapplication.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.baseapplication.Activities.Bottom_nav;
import com.example.baseapplication.Activities.GatewayToQuizForm;
import com.example.baseapplication.R;

public class QuizEndDialog extends AppCompatDialogFragment {

    TextView score;
    public static QuizEndDialog newInstance(String msg) {
        QuizEndDialog fragment = new QuizEndDialog();

        Bundle bundle = new Bundle();
        bundle.putString("score", msg); // set msg here
        fragment.setArguments(bundle);


        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.quiz_end, null);

        score = view.findViewById(R.id.score);
        score.setText(getArguments().getString("score"));

        alertDialogBuilder.setView(view)
                .setPositiveButton("Go Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getActivity(), Bottom_nav.class);
                        startActivity(intent);
                    }
                });

        return alertDialogBuilder.create();
    }
}
