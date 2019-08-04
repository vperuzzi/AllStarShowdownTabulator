package com.example.tiaperuzzi.allstarshowdowntabulator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class ClearDialog extends DialogFragment {

    ClearDialogListener listener;

    public interface ClearDialogListener {
        public void onClearDialogPositiveClick();
        public void onClearDialogNegativeClick();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (ClearDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement ClearDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to clear the board?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onClearDialogPositiveClick();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onClearDialogNegativeClick();
                    }
                });

        return builder.create();
    }
}
