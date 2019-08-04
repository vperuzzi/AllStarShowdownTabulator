package com.example.tiaperuzzi.allstarshowdowntabulator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class PointDialog extends DialogFragment {

    private EditText etPoints;

    public interface NoticeDialogListener {
        public void onPointDialogPositiveClick(int val);
        public void onPointDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement NoticeDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.point_dialog, null);
        builder.setView(view)
                .setMessage("Enter Points")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int points = Integer.parseInt(etPoints.getText().toString());
                        listener.onPointDialogPositiveClick(points);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onPointDialogNegativeClick(PointDialog.this);
                    }
                });
        etPoints = view.findViewById(R.id.et_points);
        return builder.create();
    }
}
