package com.example.baseapplication.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.baseapplication.R;

import java.util.HashMap;
import java.util.Map;


public class CreateQuestionDialog extends AppCompatDialogFragment {
    private EditText q,a,b,c,d;
    private DialogListener listener;
    private ImageButton accept,cancel;
    private CheckBox oa,ob,oc,od;

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        //Personalizamos
//
//        Resources res = getResources();
//
//        //Buttons
//        Button positive_button =  ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
//        positive_button.setBackground(res.getDrawable(R.drawable.btn_selector_dialog));
//
//        Button negative_button =  ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_NEGATIVE);
//        negative_button.setBackground(res.getDrawable(R.drawable.btn_selector_dialog));
//
//        int color = Color.parseColor("#304f5a");
//        }
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.create_qna, null);

//        accept = view.findViewById(R.id.accept);
//        cancel = view.findViewById(R.id.cancel);
        q = view.findViewById(R.id.question);
        a = view.findViewById(R.id.opA);
        b = view.findViewById(R.id.opB);
        c = view.findViewById(R.id.opC);
        d = view.findViewById(R.id.opD);
        oa = view.findViewById(R.id.optionA);
        ob = view.findViewById(R.id.optionB);
        oc = view.findViewById(R.id.optionC);
        od = view.findViewById(R.id.optionD);

        builder.setView(view).
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String ans = "";

                        if(oa.isChecked()) ans+="a";
                        if(ob.isChecked()) ans+="b";
                        if(oc.isChecked()) ans+="c";
                        if(od.isChecked()) ans+="d";

                        Map<String, String> queMap = new HashMap<>();
                        queMap.put("question", q.getText().toString());
                        queMap.put("opt_a", a.getText().toString());
                        queMap.put("opt_b", b.getText().toString());
                        queMap.put("opt_c",c.getText().toString());
                        queMap.put("opt_d", d.getText().toString());

                        listener.addQuestion(queMap,ans);

                        //listener.addQuestion(q.getText().toString(),a.getText().toString(),b.getText().toString(),c.getText().toString(),d.getText().toString(),ans);


                    }
                });

//        accept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String ans = "";
//
//                if(oa.isChecked()) ans+="a";
//                if(ob.isChecked()) ans+="b";
//                if(oc.isChecked()) ans+="c";
//                if(od.isChecked()) ans+="d";
//
//                listener.addQuestion(q.getText().toString(),a.getText().toString(),b.getText().toString(),c.getText().toString(),d.getText().toString(),ans);
//
//
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement CreateQuestionDialog");
        }
    }

    public interface DialogListener {
        void addQuestion(Map<String, String> queMap, String answer);
    }
}
