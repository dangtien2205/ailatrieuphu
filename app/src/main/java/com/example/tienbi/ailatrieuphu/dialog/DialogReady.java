package com.example.tienbi.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.tienbi.ailatrieuphu.MusicPlayer;
import com.example.tienbi.ailatrieuphu.R;

/**
 * Created by TienBi on 16/10/2016.
 */
public class DialogReady extends Dialog implements View.OnClickListener{
    private Activity activity;
    private ListnerButton listnerButton;

    public void setListnerButton(ListnerButton listnerButton) {
        this.listnerButton = listnerButton;
    }

    public DialogReady(Activity activity) {
        super(activity);
        this.activity=activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_ready);

        addControls();
    }

    private void addControls() {
        findViewById(R.id.btnYesSS).setOnClickListener(this);
        findViewById(R.id.btnNoSS).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnYesSS:
                dismiss();
                listnerButton.onPressYes();
                MusicPlayer.getMusicPlayer().stop();
                MusicPlayer.getMusicPlayer().setup(R.raw.gofind_b);
                MusicPlayer.getMusicPlayer().play(false);
                break;
            case R.id.btnNoSS:
                activity.finish();
                break;
        }
    }
    public interface ListnerButton{
        void onPressYes();
    }
}
