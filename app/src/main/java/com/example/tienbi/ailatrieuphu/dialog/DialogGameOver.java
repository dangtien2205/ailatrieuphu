package com.example.tienbi.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.tienbi.ailatrieuphu.R;

/**
 * Created by TienBi on 16/10/2016.
 */
public class DialogGameOver extends Dialog implements View.OnClickListener{
    private Activity activity;
    private int score;


    public DialogGameOver(Activity activity,int score) {
        super(activity);
        this.activity=activity;
        this.score=score;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_game_over);

        addControls();
    }

    private void addControls() {
        findViewById(R.id.btnBack).setOnClickListener(this);
        ((TextView)findViewById(R.id.txtScore)).setText("Tiền thưởng của bạn :$ "+score);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                dismiss();
                activity.finish();
                break;
        }
    }
}
