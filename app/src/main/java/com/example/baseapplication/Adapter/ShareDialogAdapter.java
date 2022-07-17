package com.example.baseapplication.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.baseapplication.Activities.GatewayToQuizForm;
import com.example.baseapplication.R;

import java.util.zip.Inflater;

public class ShareDialogAdapter extends AppCompatDialogFragment {
    EditText url;

    public static ShareDialogAdapter newInstance(String msg) {
        ShareDialogAdapter fragment = new ShareDialogAdapter();

        Bundle bundle = new Bundle();
        bundle.putString("msg", msg); // set msg here
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.share_dialog_box, null);

        url = view.findViewById(R.id.url_edittext);
        url.setText(getArguments().getString("msg"));
        alertDialogBuilder.setView(view).
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getActivity(), GatewayToQuizForm.class);
                        startActivity(intent);
                    }
                });
        return alertDialogBuilder.create();
    }
}

