package com.comp475.lhutrolleyapp.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.comp475.lhutrolleyapp.R;

public class ResetOptionsDialogFragment extends DialogFragment {

    public static String TAG = "RST";

    private NoticeDialogListener ndl;

    public interface NoticeDialogListener{
        public void onDialogPositiveClicked();
        public void onDialogNegativeClicked();
    }

    public ResetOptionsDialogFragment(NoticeDialogListener ndl) {
        super();
        this.ndl = ndl;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.reset_dialog)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.reset_title)
        .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                ndl.onDialogPositiveClicked();
            }
        })
        .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                ndl.onDialogNegativeClicked();
            }
        });

        return builder.create();
    }
}
