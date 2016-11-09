package com.example.tienbi.ailatrieuphu.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.tienbi.ailatrieuphu.MusicPlayer;
import com.example.tienbi.ailatrieuphu.R;
import com.example.tienbi.ailatrieuphu.dialog.DialogCall;
import com.example.tienbi.ailatrieuphu.dialog.DialogGameOver;
import com.example.tienbi.ailatrieuphu.dialog.DialogReady;
import com.example.tienbi.ailatrieuphu.dialog.DialogStop;
import com.example.tienbi.ailatrieuphu.manager.DatabaseManager;
import com.example.tienbi.ailatrieuphu.mode.Question;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TienBi on 15/10/2016.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout layout;
    private LinearLayout layoutMoney;
    private RelativeLayout layoutQuestion;
    private TableRow layoutHelp;

    private Animation animation1;
    private Animation animation2;
    private Animation animation3;
    private Animation animation4;
    private Animation animation5;
    private AnimationDrawable animationDrawable1;

    private boolean fistMoney = true;
    private int level;
    private int trueCase;
    private ArrayList<Question> arrQuestion;
    private int[] listAnswer = {R.id.txtAnswerA, R.id.txtAnswerB, R.id.txtAnswerC, R.id.txtAnswerD};
    private int[] listMoney = {R.id.money1, R.id.money2, R.id.money3, R.id.money4, R.id.money5,
            R.id.money6, R.id.money7, R.id.money8, R.id.money9, R.id.money10,
            R.id.money11, R.id.money12, R.id.money13, R.id.money14, R.id.money15};
    private int[] listMusicQuestion={R.raw.ques1,R.raw.ques2,R.raw.ques3,R.raw.ques4,R.raw.ques5,
                                    R.raw.ques6,R.raw.ques7,R.raw.ques8,R.raw.ques9,R.raw.ques10,
                                    R.raw.ques11,R.raw.ques12,R.raw.ques13,R.raw.ques14,R.raw.ques15};

    private int[] listScore = {0, 200, 400, 600, 800, 1000,
            2000, 4000, 6000, 8000, 10000,
            20000, 40000, 60000, 85000, 150000};
    private int[] listMusicAns={R.raw.ans_a,R.raw.ans_b,R.raw.ans_c,R.raw.ans_d};
    private int[] listMusicAnsTrue={R.raw.true_a,R.raw.true_b,R.raw.true_c,R.raw.true_d2};
    private int[] listMusicAnsFalse={R.raw.lose_a,R.raw.lose_b,R.raw.lose_c,R.raw.lose_d2};

    private ImageView iv5050;
    private ImageView ivAudience;
    private ImageView ivChange;
    private ImageView ivCall;
    private ImageView ivStop;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        addControls();
        addEvents();
        addAnimationEvents();
    }

    private void addEvents() {
        (layout.findViewById(R.id.txtAnswerA)).setOnClickListener(this);
        (layout.findViewById(R.id.txtAnswerB)).setOnClickListener(this);
        (layout.findViewById(R.id.txtAnswerC)).setOnClickListener(this);
        (layout.findViewById(R.id.txtAnswerD)).setOnClickListener(this);
    }

    private void addControls() {
        layout = (LinearLayout) findViewById(R.id.layout1);
        layoutQuestion = (RelativeLayout) layout.findViewById(R.id.layoutQuestion);
        layoutMoney = (LinearLayout) layout.findViewById(R.id.layoutMoney);
        layoutHelp = (TableRow) findViewById(R.id.layoutHelp);

        iv5050=(ImageView)findViewById(R.id.btn5050);
        ivAudience=(ImageView)findViewById(R.id.btnAudience);
        ivChange=(ImageView)findViewById(R.id.btnChange);
        ivCall=(ImageView)findViewById(R.id.btnCall);
        ivStop=(ImageView)findViewById(R.id.btnStop);

        iv5050.setOnClickListener(this);
        ivAudience.setOnClickListener(this);
        ivChange.setOnClickListener(this);
        ivCall.setOnClickListener(this);
        ivStop.setOnClickListener(this);

        animation1 = AnimationUtils.loadAnimation(this, R.anim.money_to_right);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.help_to_left);
        animation3 = AnimationUtils.loadAnimation(this, R.anim.money_to_left);
        animation4 = AnimationUtils.loadAnimation(this, R.anim.question_to_left);
        animation5 = AnimationUtils.loadAnimation(this, R.anim.question_to_right);

        layoutMoney.startAnimation(animation1);
        layoutHelp.startAnimation(animation2);

        databaseManager = new DatabaseManager(this);
        arrQuestion = databaseManager.get15Question();
        level = 1;
        setEnableHelp(false);

        playMusic(R.raw.luatchoi_b,false);
    }

    private void addAnimationEvents() {
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (fistMoney) {
                    fistMoney = false;
                    TextView txtQ5 = (TextView) layout.findViewById(R.id.money5);
                    animationDrawable1 = (AnimationDrawable) txtQ5.getBackground();
                    new CountDownTimer(6500, 2000) {
                        int n = 0;

                        @Override
                        public void onTick(long millisUntilFinished) {
                            switch (n) {
                                case 0:
                                    animationDrawable1.start();
                                    n++;
                                    break;
                                case 1:
                                    TextView txtQ10 = (TextView) layout.findViewById(R.id.money10);
                                    animationDrawable1 = (AnimationDrawable) txtQ10.getBackground();
                                    animationDrawable1.start();
                                    n++;
                                    break;
                                case 2:
                                    TextView txtQ15 = (TextView) layout.findViewById(R.id.money15);
                                    animationDrawable1 = (AnimationDrawable) txtQ15.getBackground();
                                    animationDrawable1.start();
                                    break;
                            }
                        }

                        @Override
                        public void onFinish() {
                            DialogReady dialogReady = new DialogReady(MainActivity.this);
                            dialogReady.setCancelable(false);
                            dialogReady.setListnerButton(new DialogReady.ListnerButton() {
                                @Override
                                public void onPressYes() {
                                    setEnableHelp(true);
                                    nextQuestion();
                                }
                            });
                            dialogReady.show();
                            playMusic(R.raw.ready_b,false);
                        }
                    }.start();
                } else {
                    if(level - 1 != 5 || level - 1 != 10 )
                    layout.findViewById(listMoney[level - 2]).setBackgroundResource(R.drawable.money_1);
                    nextQuestion();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutMoney.setVisibility(View.GONE);
                layoutQuestion.setVisibility(View.VISIBLE);
                layoutQuestion.startAnimation(animation4);
                setLevel();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playMusic(listMusicQuestion[level-1],false);
                MusicPlayer.getMusicPlayer().setFinish(R.raw.background_music_b);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutMoney.setVisibility(View.VISIBLE);
                layoutMoney.startAnimation(animation1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setLevel() {
        setEnabledAnswer(true);
        Question q = arrQuestion.get(level - 1);
        ((TextView) layout.findViewById(R.id.txtLevel)).setText("Câu " + level);
        ((TextView) layout.findViewById(R.id.txtQuestion)).setText(q.getQuestion());
        ((TextView) layout.findViewById(R.id.txtAnswerA)).setText("A. " + q.getCaseA());
        ((TextView) layout.findViewById(R.id.txtAnswerB)).setText("B. " + q.getCaseB());
        ((TextView) layout.findViewById(R.id.txtAnswerC)).setText("C. " + q.getCaseC());
        ((TextView) layout.findViewById(R.id.txtAnswerD)).setText("D. " + q.getCaseD());
        trueCase = q.getTrueCase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtAnswerA:
                checkAnswer(0);
                break;
            case R.id.txtAnswerB:
                checkAnswer(1);
                break;
            case R.id.txtAnswerC:
                checkAnswer(2);
                break;
            case R.id.txtAnswerD:
                checkAnswer(3);
                break;
            case R.id.btn5050:
                iv5050.setImageResource(R.drawable.atp__activity_player_button_image_help_5050_x);
                iv5050.setEnabled(false);
                int a;
                int b;
                do{
                    a = new Random().nextInt(4);
                    b = new Random().nextInt(4);
                }while (a==b || a==trueCase-1 || b==trueCase-1);
                TextView v1 =(TextView) findViewById(listAnswer[a]);
                TextView v2 =(TextView) findViewById(listAnswer[b]);
                v1.setText("");
                v2.setText("");
                v1.setEnabled(false);
                v2.setEnabled(false);
                break;
            case R.id.btnChange:
                ivChange.setImageResource(R.drawable.atp__activity_player_button_image_help_change_question_x);
                ivChange.setEnabled(false);
                arrQuestion = databaseManager.get15Question();
                setLevel();
                break;
            case R.id.btnCall:
                ivCall.setImageResource(R.drawable.atp__activity_player_button_image_help_call_x);
                ivCall.setEnabled(false);
                DialogCall dialogGameOver = new DialogCall(MainActivity.this,trueCase);
                dialogGameOver.setCancelable(false);
                dialogGameOver.show();
                break;
            case R.id.btnAudience:
                ivAudience.setImageResource(R.drawable.atp__activity_player_button_image_help_audience_x);
                ivAudience.setEnabled(false);
                break;
            case R.id.btnStop:
                stopGame();
                break;
        }
    }

    private void checkAnswer(final int indexCase) {
        playMusic(listMusicAns[indexCase],false);
        new CountDownTimer(7000, 3000) {
            int n = 0;
            View v = layout.findViewById(listAnswer[indexCase]);
            View v1 = layout.findViewById(listAnswer[trueCase - 1]);

            @Override
            public void onTick(long millisUntilFinished) {
                switch (n) {
                    case 0:
                        v.setBackgroundResource(R.drawable.answer_2);
                        n++;
                        break;
                    case 1:
                        v1.setBackgroundResource(R.drawable.animation_answer);
                        AnimationDrawable animationDrawable = (AnimationDrawable) v1.getBackground();
                        animationDrawable.start();
                        if (indexCase + 1 == trueCase) {
                            playMusic(listMusicAnsTrue[trueCase-1],false);
                            MusicPlayer.getMusicPlayer().setFinish(R.raw.background_music);
                        }else {
                            playMusic(listMusicAnsFalse[trueCase-1],false);
                            MusicPlayer.getMusicPlayer().setFinish(R.raw.bgmusic);
                        }
                        break;
                }
            }

            @Override
            public void onFinish() {
                if (indexCase + 1 == trueCase) {
                    layoutQuestion.startAnimation(animation5);
                    v.setBackgroundResource(R.drawable.answer_4);
                    v1.setBackgroundResource(R.drawable.answer_4);
                    setEnabledAnswer(false);
                    switch (level){
                        case 5:
                            playMusic(R.raw.chuc_mung_vuot_moc_01_0,true);
                            MusicPlayer.getMusicPlayer().setFinish(R.raw.background_music);
                            break;
                        case 10:
                            playMusic(R.raw.chuc_mung_vuot_moc_02_0,true);
                            MusicPlayer.getMusicPlayer().setFinish(R.raw.background_music);
                            break;
                        default:
                            break;
                    }
                    level++;
                } else {
                    DialogGameOver dialogGameOver = new DialogGameOver(MainActivity.this, listScore[level - 1]);
                    dialogGameOver.setCancelable(false);
                    dialogGameOver.show();
                }
            }
        }.start();
    }

    private void setEnabledAnswer(boolean n) {
        for (int i = 0; i < listAnswer.length; i++) {
            layout.findViewById(listAnswer[i]).setEnabled(n);
        }
    }

    private void setEnableHelp(boolean n){
        ivCall.setEnabled(n);
        ivStop.setEnabled(n);
        ivAudience.setEnabled(n);
        iv5050.setEnabled(n);
        ivChange.setEnabled(n);
    }

    private void nextQuestion() {
        new CountDownTimer(1500, 600) {
            @Override
            public void onTick(long millisUntilFinished) {
                View v1 = layout.findViewById(listMoney[level - 1]);
                v1.setBackgroundResource(R.drawable.animation_money_level);
                AnimationDrawable animationDrawable = (AnimationDrawable) v1.getBackground();
                animationDrawable.start();
            }

            @Override
            public void onFinish() {
                layoutMoney.startAnimation(animation3);
            }
        }.start();
    }
    private void stopGame(){
        DialogStop dialogStop = new DialogStop(MainActivity.this, listScore[level - 1]);
        dialogStop.setCancelable(false);
        dialogStop.show();
    }

    @Override
    public void onBackPressed() {
        stopGame();
    }
    private void playMusic(int n ,boolean isLoop){
        MusicPlayer.getMusicPlayer().stop();
        MusicPlayer.getMusicPlayer().setup(n);
        MusicPlayer.getMusicPlayer().play(isLoop);
    }
}
