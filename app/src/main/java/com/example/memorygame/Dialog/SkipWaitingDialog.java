package com.example.memorygame.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.memorygame.Language;
import com.example.memorygame.Listener.ClickListener.SkipWaitingDialogButtonAction;

public class SkipWaitingDialog extends AppCompatDialogFragment {

    private SkipWaitingDialogButtonAction listener;

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(Language.Chinese.get(Language.Key.Warning))
                .setMessage(Language.Chinese.get(Language.Key.ConfirmSkipping))
                .setPositiveButton(Language.Chinese.get(Language.Key.Confirm),
                        (dialogInterface, which) -> listener.HandleConfirmButton())
                .setNegativeButton(Language.Chinese.get(Language.Key.Cancel),
                        (dialogInterface, i) -> listener.HandleCancleButton());
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (SkipWaitingDialogButtonAction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "must implement ExampleDialogListener");
        }
    }
}
