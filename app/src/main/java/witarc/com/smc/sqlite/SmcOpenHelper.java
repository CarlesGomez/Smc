package witarc.com.smc.sqlite;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import witarc.com.smc.R;

/**
 * Created by Carechimbo on 24/06/2014.
 */

public class SmcOpenHelper extends SQLiteOpenHelper {

    //region ATRIBUTS
    Context c;
    //endregion

    //region CONSTRUCTORS
    public SmcOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        c=context;
    }
    //endregion

    //region EVENTS
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ReadFile(c, R.raw.create_db_sqlite, db);
        Log.i("onCreate", "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //endregion

    //region MÈTODES
    /**
     * Llegeix un fitxer per tal de executar les seves sentències a la BD SQlite.
     * @param c contexte.
     * @param raw fitxer que es tractarà.
     * @param db base de dades en la qual s'executaràn les comandes.
     */
    private void ReadFile(Context c, int raw, SQLiteDatabase db){
        String line;
        try{
            InputStream rawFile = c.getResources().openRawResource(raw);
            BufferedReader brIn = new BufferedReader(new InputStreamReader(rawFile));
            while ((line=brIn.readLine())!=null){
                db.execSQL(line);
            }
            rawFile.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //endregion
}

