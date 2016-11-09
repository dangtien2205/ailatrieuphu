package com.example.tienbi.ailatrieuphu.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.tienbi.ailatrieuphu.mode.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by TienBi on 09/10/2016.
 */
public class DatabaseManager {
    private Context context;
    String DATABASE_NAME = "Question.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private SQLiteDatabase sqLiteDatabase=null;

    public DatabaseManager(Context context) {
        this.context = context;
        xuLySaoChepCSDL();
    }

    private void xuLySaoChepCSDL() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                copyDataBaseFromAsset();
                Toast.makeText(context, "Sao chép thành công", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void copyDataBaseFromAsset() {
        try {
            InputStream myInput = context.getAssets().open("data/"+DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            File f = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {
            Log.e("Loi", ex.toString());
        }
    }

    private String layDuongDanLuuTru() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    private void openDataBase() {
        //if (sqLiteDatabase == null)
            sqLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    private void closeDataBase(){
        if(sqLiteDatabase!=null && sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }
    public void insert(String nametable, ContentValues contentValues){
        openDataBase();
        sqLiteDatabase.insert(nametable,null,contentValues);
        closeDataBase();
    }
    public void delete(String nametable,String whereclause,String[] whereargs){
        openDataBase();
        sqLiteDatabase.delete(nametable,whereclause,whereargs);
        closeDataBase();
    }
    public Question getOneQuestion(String nametable){
        openDataBase();
        Question q = new Question();
        Cursor cursor =sqLiteDatabase.rawQuery("Select * from "+nametable+" order by Random() limit 1",null);
        if(cursor==null||cursor.getCount()==0) {
            return null;
        }
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String question = cursor.getString(1);
            String caseA = cursor.getString(cursor.getColumnIndex("CaseA"));
            String caseB = cursor.getString(3);
            String caseC = cursor.getString(4);
            String caseD = cursor.getString(5);
            int caseTrue = cursor.getInt(6);
            q= new Question(id,question,caseA,caseB,caseC,caseD,caseTrue);
        }
        closeDataBase();
        return q;
    }
    public ArrayList<Question> get15Question(){
        openDataBase();
        ArrayList<Question> list=new ArrayList<>();
        for (int i = 1; i <= 15 ; i++) {
            Question question=getOneQuestion("Question" +i);
            if(question!=null)
                list.add(question);
        }
        closeDataBase();
        return list;
    }
}
