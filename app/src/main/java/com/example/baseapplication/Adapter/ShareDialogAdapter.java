package com.example.baseapplication.Adapter;

import static androidx.core.content.ContextCompat.getSystemService;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.baseapplication.Activities.GatewayToQuizForm;
import com.example.baseapplication.R;

import java.util.zip.Inflater;

public class ShareDialogAdapter extends AppCompatDialogFragment {
    EditText url;
    Button copyBtn;

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
        copyBtn = view.findViewById(R.id.btn_copy);

        url = view.findViewById(R.id.url_edittext);
        url.setText(getArguments().getString("msg"));

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = (ClipData) ClipData.newPlainText("url", getArguments().getString("msg"));
                clipboardManager.setPrimaryClip(data);
                Log.d("copy","done");

                Toast.makeText(v.getContext(),"Link Copied!", Toast.LENGTH_SHORT).show();
            }
        });

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

