package com.example.tienbi.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tienbi.ailatrieuphu.R;

import java.util.Random;

/**
 * Created by TienBi on 16/10/2016.
 */
public class DialogCall extends Dialog implements View.OnClickListener{
    private Activity activity;
    private LinearLayout layout;
    private TextView txtGoiY;
    private int caseTrue;
    private String[] listAns={"A","B","C","D"};

    public DialogCall(Activity activity,int caseTrue) {
        super(activity);
        this.activity=activity;
        this.caseTrue=caseTrue;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_call);

        addControls();
    }

    private void addControls() {
        findViewById(R.id.btnCall1).setOnClickListener(this);
        findViewById(R.id.btnCall2).setOnClickListener(this);
        findViewById(R.id.btnCall3).setOnClickListener(this);
        findViewById(R.id.btnCall4).setOnClickListener(this);
        findViewById(R.id.btnOK).setOnClickListener(this);

        layout =(LinearLayout)findViewById(R.id.layoutCall);
        txtGoiY=(TextView)findViewById(R.id.txtGoiY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCall1:
                suggest();
                break;
            case R.id.btnCall2:
                suggest();
                break;
            case R.id.btnCall3:
                suggest();
                break;
            case R.id.btnCall4:
                suggest();
                break;
            case R.id.btnOK:
                dismiss();
                break;
            default:
                break;
        }
    }
    private void suggest(){
        layout.setVisibility(View.GONE);
        findViewById(R.id.txtGoiY).setVisibility(View.VISIBLE);
        findViewById(R.id.btnOK).setVisibility(View.VISIBLE);
        int a = new Random().nextInt(10);
        if(a<8){
            txtGoiY.setText("Đáp án gợi ý là :"+listAns[caseTrue-1]);
        }else {
            int n;
            do {
                n=new Random().nextInt(4);
                txtGoiY.setText("Đáp án gợi ý là :"+listAns[caseTrue-1]);
            }while (n==caseTrue-1);
        }
    }
}
