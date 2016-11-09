package com.example.tienbi.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.tienbi.ailatrieuphu.R;

/**
 * Created by TienBi on 16/10/2016.
 */
public class DialogStop extends Dialog implements View.OnClickListener{
    private Activity activity;
    private int score;

    public DialogStop(Activity activity,int score) {
        super(activity);
        this.activity=activity;
        this.score=score;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_stop);

        addControls();
    }

    private void addControls() {
        findViewById(R.id.btnYesStop).setOnClickListener(this);
        findViewById(R.id.btnNoStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnYesStop:
                DialogGameOver dialogGameOver = new DialogGameOver(activity,score);
                dialogGameOver.setCancelable(false);
                dialogGameOver.show();
                break;
            case R.id.btnNoStop:
                dismiss();
                break;
        }
    }
}
