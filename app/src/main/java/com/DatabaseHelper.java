package com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.Classes.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static DatabaseHelper mInstance;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Wizenet";
    // Contacts table name
    private static final String TABLE_CONTROL_PANEL = "ControlPanel";
    private static final String TABLE_MESSAGES = "Messages";
    private static final String mgnet_items = "mgnet_items";
    private static final String mgnet_calls = "mgnet_calls";
    private static final String CallStatus = "CallStatus";
    private static final String Calltime = "Calltime";

    //Table Columns mgnet_calls
    //CID, AID, StartDate, subject, CallType, TechnicianID, CPhone, ProblemID,
     //comments, Pmakat, InternalSN, Pname, OriginID, contractID, cntrctDate, CallStatus, resolution, CallTypeID, Cname, priorityID, contctCode,CallRefID

    //CallStatusID,CallStatusName,CallStatusOrder,* from CallStatus

    // Table Columns ControlPanel
    private static final String ID = "id";
    private static final String KEY = "_key";
    private static final String VALUE = "_value";
    private static final String DESCRIPTION = "_description";
    public static final String DEMOURL = "http://main.wizenet.co.il/webservices/freelance.asmx";//default
    //Table Columns MESSAGES
    private static final String MsgID = "msgID";
    private static final String MsgSUBJECT ="msgSubject";
    private static final String MsgCOMMENT = "msgComment";
    private static final String MsgURL = "msgUrl";
    private static final String MsgDATE = "msgDate";
    private static final String MsgREAD = "msgRead";
    private static final String MsgTYPE = "msgType";

    //Table Columns TEMP
    //private static final String TMP_PARENTID = "msgID";
    //private static final String MsgSUBJECT ="msgSubject";
    //private static final String MsgCOMMENT = "msgComment";
    //private static final String MsgURL = "msgUrl";
    //private static final String MsgDATE = "msgDate";

    static private Context mCtx;

    public static DatabaseHelper getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
            mCtx = ctx;
        }
        return mInstance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mCtx = context;
    }
    //region unusble
    String CREATE_mgnet_calls =
            "CREATE TABLE " + mgnet_calls + "("
                    + "CallID" +  " INTEGER, "
                    + "AID" + " INTEGER, "
                    + "createDate" + " TEXT, "
                    + "subject" + " TEXT, "
                    + "CallType" + " TEXT, "
                    + "TechnicianID" + " INTEGER, "
                    + "CPhone" + " TEXT, "
                    + "ProblemID" + " INTEGER, "
                    + "InternalSN" + " TEXT, "
                    + "Pname" + " TEXT, "
                    + "OriginID" + " INTEGER, "
                    + "contractID" + " INTEGER, "
                    + "cntrctDate" + " TEXT, "
                    + "CallStatus" + " INTEGER "
                    + "resolution" + " TEXT, "
                    + "CallTypeID" + " INTEGER "
                    + "priorityID" + " TEXT, "
                    + "contctCode" + " INTEGER "
                    + "CallRefID" + " TEXT "
                    + ")";
    //endregion   cal
    // callid=46707, CallStatsName = 3

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IS_Actions = "CREATE TABLE " + "IS_Actions" + "("
       + "actionID" + " INTEGER,"
        +"taskID"+ " INTEGER,"
        +"actionDate" + " TEXT,"
        +"actionStartDate" + " TEXT,"
        +"actionDue" + " TEXT,"
        +"actionDesc" + " TEXT,"
        +"comments" + " TEXT,"
        +"priorityID"+ " INTEGER,"
        +"statusID"+ " INTEGER,"
        +"reminderID" + " TEXT,"
        +"ownerID"+ " INTEGER,"
        +"userID"+ " INTEGER,"
        +"WorkHours"+ " TEXT,"
        +"WorkEstHours"+ " TEXT,"
        +"[Create]"+ " TEXT,"
        +"LastUpdate"+ " TEXT,"
        +"actionLink"+ " TEXT,"
        +"depID"+ " INTEGER,"
        +"actionRef"+ " TEXT,"
        +"userCfname"+ " TEXT,"
        +"userClname"+ " TEXT,"
        +"userCemail"+ " TEXT,"
        +"userCtypeID"+ " INTEGER,"
        +"ownerCfname"+ " TEXT,"
        +"ownerClname"+ " TEXT,"
        +"ownerCemail"+ " TEXT,"
        +"ownerCtypeID"+ " INTEGER,"
        +"projectID"+ " INTEGER,"
        +"statusName"+ " TEXT,"
        +"PriorityName"+ " TEXT,"
        +"actionType"+ " TEXT,"
        +"actionSdate"+ " TEXT,"
        +"actionEdate"+ " TEXT,"
        +"WorkHoursM"+ " TEXT,"
        +"WorkEstHoursM"+ " TEXT,"
        +"actionPrice"+ " TEXT,"
        +"statusColor"+ " TEXT,"
        +"taskSummery"+ " TEXT,"
        +"projectSummery"+ " TEXT,"
        +"projectType"+ " TEXT,"
        +"actionNum"+ " TEXT,"
        +"actionFrom"+ " TEXT,"
        +"actionDays"+ " TEXT,"
        +"ParentActionID"+ " INTEGER,"
        +"remindertime"+ " TEXT,"
        +"Expr1"+ " TEXT,"
        +"projectDesc" + " TEXT"
        + ")";
        String CREATE_call_time =
                "CREATE TABLE " + "Calltime" + "("
                        + "CTID" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "CallID" +  " INTEGER, "
                        + "CallStartTime" +  " TEXT, "
                        + "Minute" + " TEXT, "
                        + "CTcomment" + " TEXT, "
                        + "ctq" + " TEXT "
                        + ")";

        String CREATE_call_offline=
                "CREATE TABLE " + "call_offline" + "("
                        + "CallID" +  " INTEGER, "
                        + "statusID"+  " INTEGER, "
                        + "internalSN"+ " TEXT, "
                        + "techAnswer"+ " TEXT "
                        + ")";

        String CREATE_mgnet_calls=
                "CREATE TABLE " + mgnet_calls + "("
                        + "CallID" +  " INTEGER, "
                        + "AID"+  " INTEGER, "
                        + "CID"+  " INTEGER, "
                        + "CreateDate"+ " TEXT, "
                        + "EndDate"+ " TEXT, "
                        + "CallEmail"+ " TEXT, "
                        + "statusID"+  " INTEGER, "
                        + "CallPriority"+ " TEXT, "
                        + "subject"+ " TEXT, "
                        + "comments"+ " TEXT, "
                        + "IsClose"+  " INTEGER, "
                        + "IsRead"+  " INTEGER, "
                        + "CallType"+ " TEXT, "
                        + "CallReminderDate"+ " TEXT, "
                        + "CallUpdate"+ " TEXT, "
                        + "resolution"+ " TEXT, "
                        + "cntrctDate"+ " TEXT, "
                        + "TechnicianID"+  " INTEGER, "
                        + "statusName"+ " TEXT, "
                        + "PcatID"+  " INTEGER, "
                        + "internalSN"+ " TEXT, "
                        + "Pmakat"+ " TEXT, "
                        + "Pname"+ " TEXT, "
                        + "contractID"+ " TEXT, "
                        + "Cphone"+ " TEXT, "
                        + "OriginID"+  " INTEGER, "
                        + "ProblemTypeID"+  " INTEGER, "
                        + "CallTypeID"+  " INTEGER, "
                        + "priorityID"+  " TEXT, "
                        + "OriginName"+ " TEXT, "
                        + "problemTypeName"+ " TEXT, "
                        + "CallTypeName"+ " TEXT, "
                        + "Cname"+ " TEXT, "
                        + "Cemail"+ " TEXT, "
                        + "contctCode"+  " INTEGER, "
                        + "callStartTime"+ " TEXT, "
                        + "callEndTime"+ " TEXT, "
                        + "Ccompany"+ " TEXT, "
                        + "Clocation"+ " TEXT, "
                        + "callOrder"+  " INTEGER, "
                        + "Caddress"+ " TEXT, "
                        + "Ccity"+ " TEXT, "
                        + "Ccomments"+ " TEXT, "
                        + "Cfname"+ " TEXT, "
                        + "Clname"+ " TEXT, "
                        + "techName"+ " TEXT, "
                        + "Aname"+ " TEXT, "
                        + "ContctName"+ " TEXT, "
                        + "ContctAddress"+ " TEXT, "
                        + "ContctCity"+ " TEXT, "
                        + "ContctCell"+ " TEXT, "
                        + "ContctPhone"+ " TEXT, "
                        + "Ccell"+ " TEXT, "
                        + "techColor"+ " TEXT, "
                        + "ContctCemail"+ " TEXT, "
                        + "CallParentID"+ " TEXT, "
                        + "state"+ " TEXT, "
                        + "sla"+ " TEXT "
                        + ")";
        String CREATE_mgnet_items =
                "CREATE TABLE " + "mgnet_items" + "("
//                        + "id" +  " TEXT PRIMARY KEY AUTOINCREMENT,"
                        + "Pname" + " TEXT, "
                        + "Pmakat" + " TEXT, "
                        + "Pprice" + " TEXT, "
                        + "Poprice" + " TEXT "
                        + ")";
        //CallStatusID,CallStatusName,CallStatusOrder
        String CREATE_CallStatus =
                "CREATE TABLE " + CallStatus + "("
                        + "CallStatusID" + " INTEGER, "
                        + "CallStatusName" + " TEXT, "
                        + "CallStatusOrder" + " INTEGER "
                        + ")";
        String CREATE_mgnet_client_items =
                "CREATE TABLE " + "mgnet_client_items" + "("
//                        + "id" +  " TEXT PRIMARY KEY AUTOINCREMENT,"
                        + "Pname" + " TEXT, "
                        + "Pmakat" + " TEXT, "
                        + "Pprice" + " TEXT, "
                        + "Poprice" + " TEXT "
                        + ")";
        String CREATE_CP_TABLE =
                "CREATE TABLE " + TABLE_CONTROL_PANEL + "("
                        + KEY +  " TEXT PRIMARY KEY,"
                        + VALUE + " TEXT, "
                        + DESCRIPTION + " TEXT "
                        + ")";

        String CREATE_MESSAGES_TABLE =
                "CREATE TABLE " + TABLE_MESSAGES + "("
                        + MsgID +  " TEXT PRIMARY KEY,"
                        + MsgSUBJECT + " TEXT, "
                        + MsgCOMMENT + " TEXT, "
                        + MsgURL + " TEXT, "
                        + MsgDATE + " TEXT, "
                        + MsgREAD + " TEXT, "
                        + MsgTYPE + " TEXT "
                        + ")";
        db.execSQL(CREATE_IS_Actions);
        db.execSQL(CREATE_CallStatus);
        db.execSQL(CREATE_mgnet_items);
        db.execSQL(CREATE_CP_TABLE);
        db.execSQL(CREATE_MESSAGES_TABLE);
        db.execSQL(CREATE_mgnet_client_items);
        db.execSQL(CREATE_mgnet_calls);
        db.execSQL(CREATE_call_offline);
        db.execSQL(CREATE_call_time);
        //db.execSQL("DROP TABLE "+TABLE_CONTROL_PANEL+" ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTROL_PANEL);
// Creating tables again
        onCreate(db);
    }

    public String getScalarByCountQuery(String s){
        Helper helper = new Helper();
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT count(*) FROM " + TABLE_CONTROL_PANEL
        String count = s;
        int icount = 0;
        try{
            Cursor mcursor = db.rawQuery(count, null);
            mcursor.moveToFirst();
            icount = mcursor.getInt(0);
            Log.e("mytag",Integer.toString(icount));
        }catch(Exception e){
            helper.LogPrintExStackTrace(e);
        }
        //db.close();
        return Integer.toString(icount) ;
    }
    public String getScalarBySql(String s){
        Helper helper = new Helper();
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT count(*) FROM " + TABLE_CONTROL_PANEL
        String count = s;
        String icount = "";
        try{
            Cursor mcursor = db.rawQuery(count, null);
            mcursor.moveToFirst();
            icount = mcursor.getString(0);
            Log.e("mytag",(icount));
        }catch(Exception e){
            helper.LogPrintExStackTrace(e);
        }
        db.close();
        return (icount) ;
    }

    public  boolean columnExistsInTable(String table, String columnToCheck) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
             db = this.getReadableDatabase();
            //query a row. don't acquire db lock
            cursor = db.rawQuery("SELECT top 1 * FROM " + table + " LIMIT 0", null);

            // getColumnIndex()  will return the index of the column
            //in the table if it exists, otherwise it will return -1
            if (cursor.getColumnIndex(columnToCheck) != -1) {
                //great, the column exists
                return true;
            }else {
                //sorry, the column does not exist
                return false;
            }

        } catch (SQLiteException Exp) {
            //Something went wrong with SQLite.
            //If the table exists and your query was good,
            //the problem is likely that the column doesn't exist in the table.
            return false;
        }
    }
    public boolean isTableExists(String tableName) {

        try {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        }catch (Exception e){
            Helper h = new Helper();
            Log.e("mytag",e.getMessage());
            h.LogPrintExStackTrace(e);
        }
        return false;
    }
    public String[] getTableColumns(){
        Log.e("mytag","getTableColums");
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Log.e("mytag","getTableColums1");
            Cursor dbCursor = db.query("IS_Actions", null, null, null, null, null, null);
            String[] columnNames = dbCursor.getColumnNames();

            String ret = "IS_Actions:";
            for (String c:columnNames) {
                ret += c + ", ";
            }
            Log.e("mytag",ret);
            return columnNames;
        }catch(Exception e){
            Helper h = new Helper();
            h.LogPrintExStackTrace(e);
            return null;
        }

    }
    public boolean createColumnToISActions(String column,boolean isTableExist){
        getTableColumns();
        String IS_Actions = "IS_Actions";
        boolean flag = false;
        Helper h = new Helper();
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String CREATE_IS_Actions = "CREATE TABLE " + "IS_Actions" + "("
                    + "actionID" + " INTEGER,"
                    +"taskID" + " INTEGER,"
                    +"actionDate" + " TEXT,"
                    +"actionStartDate" + " TEXT,"
                    +"actionDue" + " TEXT,"
                    +"actionDesc" + " TEXT,"
                    +"comments" + " TEXT,"
                    +"priorityID"+ " INTEGER,"
                    +"statusID"+ " INTEGER,"
                    +"reminderID" + " TEXT,"
                    +"ownerID"+ " INTEGER,"
                    +"userID"+ " INTEGER,"
                    +"WorkHours"+ " TEXT,"
                    +"WorkEstHours"+ " TEXT,"
                    +"[Create]"+ " TEXT,"
                    +"LastUpdate"+ " TEXT,"
                    +"actionLink"+ " TEXT,"
                    +"depID"+ " INTEGER,"
                    +"actionRef"+ " TEXT,"
                    +"userCfname"+ " TEXT,"
                    +"userClname"+ " TEXT,"
                    +"userCemail"+ " TEXT,"
                    +"userCtypeID"+ " INTEGER,"
                    +"ownerCfname"+ " TEXT,"
                    +"ownerClname"+ " TEXT,"
                    +"ownerCemail"+ " TEXT,"
                    +"ownerCtypeID"+ " INTEGER,"
                    +"projectID"+ " INTEGER,"
                    +"statusName"+ " TEXT,"
                    +"PriorityName"+ " TEXT,"
                    +"actionType"+ " TEXT,"
                    +"actionSdate"+ " TEXT,"
                    +"actionEdate"+ " TEXT,"
                    +"WorkHoursM"+ " TEXT,"
                    +"WorkEstHoursM"+ " TEXT,"
                    +"actionPrice"+ " TEXT,"
                    +"statusColor"+ " TEXT,"
                    +"taskSummery"+ " TEXT,"
                    +"projectSummery"+ " TEXT,"
                    +"projectType"+ " TEXT,"
                    +"actionNum"+ " TEXT,"
                    +"actionFrom"+ " TEXT,"
                    +"actionDays"+ " TEXT,"
                    +"ParentActionID"+ " INTEGER,"
                    +"remindertime"+ " TEXT,"
                    +"Expr1"+ " TEXT,"
                    +"projectDesc" + " TEXT"
                    + ")";
            if (isTableExist){
                db.execSQL("DROP TABLE IF EXISTS '" + IS_Actions + "_old" + "'");
                db.execSQL("ALTER TABLE " + IS_Actions + " RENAME TO " + IS_Actions + "_old;");
                Log.e("mytag","step 1");
            }

            try{
                db.execSQL(CREATE_IS_Actions);
            }catch (Exception e){
                Log.e("mytag","exception create exe: " + e.getMessage());
                //String bla = CREATE_mgnet_calls.replace("CREATE TABLE","ALTER TABLE");
                //db.execSQL(bla);
            }
            if (isTableExist){
                db.execSQL("DROP TABLE " + IS_Actions + "_old;");
                Log.e("mytag","step 2");
            }

            //Log.e("mytag","last time: " + columnExistsInTable("mgnet_calls","sla"));
            if (!columnExistsInTable(IS_Actions,column)){
                try{
                    db.execSQL("ALTER TABLE " + IS_Actions + " ADD COLUMN " + column + " TEXT;");
                    Log.e("mytag","success to add " + column + "to calls");

                }catch (Exception e){
                    Log.e("mytag","err step 4, " + e.getMessage());
                    h.LogPrintExStackTrace(e);
                }
            }

            flag = true;
        }catch (Exception e){

            Log.e("mytag",e.getMessage());
            h.LogPrintExStackTrace(e);
        }
        return flag;
    }
    public boolean createColumnToCalls(String column,boolean isTableExist){
        String mgnet_calls = "mgnet_calls";
        boolean flag = false;
        Helper h = new Helper();
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String CREATE_mgnet_calls=
                    "CREATE TABLE " + mgnet_calls + "("
                            + "CallID" +  " INTEGER, "
                            + "AID"+  " INTEGER, "
                            + "CID"+  " INTEGER, "
                            + "CreateDate"+ " TEXT, "
                            + "EndDate"+ " TEXT, "
                            + "CallEmail"+ " TEXT, "
                            + "statusID"+  " INTEGER, "
                            + "CallPriority"+ " TEXT, "
                            + "subject"+ " TEXT, "
                            + "comments"+ " TEXT, "
                            + "IsClose"+  " INTEGER, "
                            + "IsRead"+  " INTEGER, "
                            + "CallType"+ " TEXT, "
                            + "CallReminderDate"+ " TEXT, "
                            + "CallUpdate"+ " TEXT, "
                            + "resolution"+ " TEXT, "
                            + "cntrctDate"+ " TEXT, "
                            + "TechnicianID"+  " INTEGER, "
                            + "statusName"+ " TEXT, "
                            + "PcatID"+  " INTEGER, "
                            + "internalSN"+ " TEXT, "
                            + "Pmakat"+ " TEXT, "
                            + "Pname"+ " TEXT, "
                            + "contractID"+ " TEXT, "
                            + "Cphone"+ " TEXT, "
                            + "OriginID"+  " INTEGER, "
                            + "ProblemTypeID"+  " INTEGER, "
                            + "CallTypeID"+  " INTEGER, "
                            + "priorityID"+  " TEXT, "
                            + "OriginName"+ " TEXT, "
                            + "problemTypeName"+ " TEXT, "
                            + "CallTypeName"+ " TEXT, "
                            + "Cname"+ " TEXT, "
                            + "Cemail"+ " TEXT, "
                            + "contctCode"+  " INTEGER, "
                            + "callStartTime"+ " TEXT, "
                            + "callEndTime"+ " TEXT, "
                            + "Ccompany"+ " TEXT, "
                            + "Clocation"+ " TEXT, "
                            + "callOrder"+  " INTEGER, "
                            + "Caddress"+ " TEXT, "
                            + "Ccity"+ " TEXT, "
                            + "Ccomments"+ " TEXT, "
                            + "Cfname"+ " TEXT, "
                            + "Clname"+ " TEXT, "
                            + "techName"+ " TEXT, "
                            + "Aname"+ " TEXT, "
                            + "ContctName"+ " TEXT, "
                            + "ContctAddress"+ " TEXT, "
                            + "ContctCity"+ " TEXT, "
                            + "ContctCell"+ " TEXT, "
                            + "ContctPhone"+ " TEXT, "
                            + "Ccell"+ " TEXT, "
                            + "techColor"+ " TEXT, "
                            + "ContctCemail"+ " TEXT, "
                            + "CallParentID"+ " TEXT, "
                            + "state"+ " TEXT "
                            + "sla"+ " TEXT "
                            + ")";
            if (isTableExist){
                db.execSQL("DROP TABLE IF EXISTS '" + mgnet_calls + "_old" + "'");
                db.execSQL("ALTER TABLE " + mgnet_calls + " RENAME TO " + mgnet_calls + "_old;");
                Log.e("mytag","step 1");
            }

            try{
            db.execSQL(CREATE_mgnet_calls);
            }catch (Exception e){
                Log.e("mytag","exception create exe: " + e.getMessage());
                //String bla = CREATE_mgnet_calls.replace("CREATE TABLE","ALTER TABLE");
                //db.execSQL(bla);
            }
            if (isTableExist){
                db.execSQL("DROP TABLE " + mgnet_calls + "_old;");
                Log.e("mytag","step 2");
            }

            Log.e("mytag","last time: " + columnExistsInTable("mgnet_calls","sla"));
            if (!columnExistsInTable("mgnet_calls","sla")){
                Log.e("mytag","step 3");
                try{
                    db.execSQL("ALTER TABLE " + mgnet_calls + " ADD COLUMN " + column + " TEXT;");
                    Log.e("mytag","success to add " + column + "to calls");

                }catch (Exception e){
                    Log.e("mytag","err step 4, " + e.getMessage());
                    h.LogPrintExStackTrace(e);
                }
            }

            flag = true;
        }catch (Exception e){

            Log.e("mytag",e.getMessage());
            h.LogPrintExStackTrace(e);
        }
        return flag;
    }
    public boolean createColumnToCalltime(String column,boolean isTableExist){
        String Calltime = "Calltime";
        boolean flag = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String CREATE_call_time =
                    "CREATE TABLE " + "Calltime" + "("
                            + "CTID" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "CallID" +  " INTEGER, "
                            + "CallStartTime" +  " TEXT, "
                            + "Minute" + " TEXT, "
                            + "CTcomment" + " TEXT ,"
                            + "ctq" + " TEXT "
                            + ")";
            if (isTableExist){
                db.execSQL("ALTER TABLE " + Calltime + " RENAME TO " + Calltime + "_old;");
            }
            db.execSQL(CREATE_call_time);
            if (isTableExist) {
                db.execSQL("DROP TABLE " + Calltime + "_old;");
            }
            if (!column.equals("")){
                db.execSQL("ALTER TABLE " + Calltime + " ADD COLUMN " + column + " TEXT;");
            }
            flag = true;
        }catch (Exception e){
            Helper h = new Helper();
            Log.e("mytag",e.getMessage());
            h.LogPrintExStackTrace(e);
        }
        return flag;
    }
    public boolean createColumnToCalls_Offline(String column,boolean isTableExist){
        String call_offline = "call_offline";
        boolean flag = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String CREATE_call_offline=
                    "CREATE TABLE " + "call_offline" + "("
                            + "CallID" +  " INTEGER, "
                            + "statusID"+  " INTEGER, "
                            + "internalSN"+ " TEXT, "
                            + "techAnswer"+ " TEXT "
                            + ")";
            if (isTableExist){
                db.execSQL("ALTER TABLE " + call_offline + " RENAME TO " + call_offline + "_old;");
            }

            db.execSQL(CREATE_call_offline);
            if (isTableExist) {
                db.execSQL("DROP TABLE " + call_offline + "_old;");
            }
            if (!column.equals("")){
                db.execSQL("ALTER TABLE " + call_offline + " ADD COLUMN " + column + " TEXT;");
            }
            flag = true;
        }catch (Exception e){
            Helper h = new Helper();
            Log.e("mytag",e.getMessage());
            h.LogPrintExStackTrace(e);
        }
        return flag;
    }
    public boolean createColumnToCP(String column,boolean isTableExist){
        String ControlPanel = "ControlPanel";
        boolean flag = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String CREATE_CP_TABLE =
                    "CREATE TABLE " + TABLE_CONTROL_PANEL + "("
                            + KEY +  " TEXT PRIMARY KEY,"
                            + VALUE + " TEXT, "
                            + DESCRIPTION + " TEXT "
                            + ")";

            if (isTableExist){
                db.execSQL("ALTER TABLE " + ControlPanel + " RENAME TO " + ControlPanel + "_old;");
            }

            db.execSQL(CREATE_CP_TABLE);
            if (isTableExist){
                db.execSQL("DROP TABLE " + ControlPanel + "_old;");
            }

            if (!column.equals("")) {
                db.execSQL("ALTER TABLE " + ControlPanel + " ADD COLUMN " + column + " TEXT;");
            }
            flag = true;
        }catch (Exception e){
            Helper h = new Helper();
            Log.e("mytag",e.getMessage());
            h.LogPrintExStackTrace(e);
        }
        return flag;
    }
    public boolean delete_call_time() {
        boolean flag = true;
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete("Calltime", null, null);
            //db.close();

        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }
    public boolean delete_IS_Actions_Table() {
        boolean flag = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS IS_Actions");
            //db.delete("IS_Actions", null, null);
            //db.close();
            flag = true;
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }

    public boolean delete_IS_Actions_Rows() {
        boolean flag = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from IS_Actions where actionID < 0");
            //db.close();
            flag = true;
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }
    //region calltime
    public boolean add_calltime(Calltime ct){
        boolean flag = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("CallID" , ct.getCallID());
            values.put("CallStartTime", ct.getCallStartTime());
            values.put("Minute", ct.getMinute());
            values.put("CTcomment", ct.getCTcomment().trim());
            values.put("ctq", ct.getCtq());
            db.insert("Calltime", null, values);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }
    public boolean update_calltime(Calltime ct) {
        //Log.e("mytag",ct.toString());
        boolean flag = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("CallID" , ct.getCallID());
            values.put("CallStartTime", ct.getCallStartTime());
            values.put("Minute", ct.getMinute());
            values.put("CTcomment", ct.getCTcomment().trim());
            values.put("ctq", ct.getCtq());
            db.update("Calltime", values, "CTID = " + ct.getCTID(), null);
            flag = true;
        }catch (Exception e){
            Helper h = new Helper();
            h.LogPrintExStackTrace(e);
            Log.e("MYTAG",e.getMessage());
        }
        return flag;

    }
    //endregion

   //region call_offline
    public boolean add_call_offline(Call_offline co){
        boolean flag = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("CallID" , co.getCallID());
            values.put("statusID", co.getStatusID());
            values.put("internalSN", co.getInternalSN());
            values.put("techAnswer", co.getTechAnswer().trim());
            // Inserting Row
            db.insert("call_offline", null, values);
            // Closing database connection
            //db.close();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }

    public JSONArray getJsonResultsFromTable(String tableName)
    {
        //Log.e("mytag","hello from db");
        SQLiteDatabase db = this.getReadableDatabase();
        String searchQuery = "";
        if (tableName.equals("IS_Actions_Offline")){
            searchQuery = "SELECT  * FROM IS_Actions where actionID < 0" ;

        }else{
            searchQuery = "SELECT  * FROM " + tableName;

        }
        Cursor cursor = db.rawQuery(searchQuery, null );

        JSONArray resultSet = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }
    public JSONArray getJsonResults()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String searchQuery = "SELECT  * FROM " + "call_offline";
        Cursor cursor = db.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }
    public List<Call_offline> getCall_offline() {
        List<Call_offline> callList = new ArrayList<Call_offline>();
// Select All Query
        String selectQuery ="";
        selectQuery = "SELECT * FROM call_offline " ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Call_offline c= new Call_offline(
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallID"))),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("statusID"))),
                        cursor.getString(cursor.getColumnIndex("internalSN")),
                        cursor.getString(cursor.getColumnIndex("techAnswer")));
// Adding contact to list
                callList.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return callList;
    }
    public boolean deleteAllCall_offline() {
        boolean flag = true;
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete("Call_offline", null, null);
            db.close();

        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }
    public boolean delete_all_offline_is_actions() {
        boolean flag = true;
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete("Call_offline", null, null);
            db.close();

        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }
    //endregion

//region CALLS
public void updateSpecificValueInTable2(String table,String primarykey,String primaryval,String fieldName,String fieldValue) {
    try{
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + table + " SET " + fieldName + " = " + fieldValue + " WHERE " + primarykey + " = "+ primaryval + "";
        Log.e("mytag",strSQL);
        db.execSQL(strSQL);
        Log.e("mytag","success to update value2");
    }catch (Exception e){
        Log.e("mytag",e.getMessage());
    }
}
    public List<IS_Action> getISActions(String sortby) {
        List<IS_Action> actions = new ArrayList<IS_Action>();
        try{
// Select All Query
        String selectQuery ="";
        selectQuery = "SELECT * FROM IS_Actions where 1=1   " ;
        if ((!sortby.trim().equals("") && (sortby != "top1"))){
            selectQuery+=  sortby + "";
            //selectQuery+= "  order by " + sortby + "";
        }
        if (sortby == "top1"){
            selectQuery+=   " order by actionID limit 1";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        //Log.e("mytag","sql: " +selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
            Log.e("mytag","row count is_actions: " +cursor.getCount());
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                IS_Action action= new IS_Action(
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("actionID"))),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("taskID"))),
                        cursor.getString(cursor.getColumnIndex("actionDate")),cursor.getString(cursor.getColumnIndex("actionStartDate")),
                        cursor.getString(cursor.getColumnIndex("actionDue")),
                        cursor.getString(cursor.getColumnIndex("actionDesc")),
                        cursor.getString(cursor.getColumnIndex("comments")),
                        cursor.getString(cursor.getColumnIndex("priorityID")),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("statusID"))),
                        cursor.getString(cursor.getColumnIndex("reminderID")),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("ownerID"))),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("userID"))),
                        cursor.getString(cursor.getColumnIndex("WorkHours")),
                        cursor.getString(cursor.getColumnIndex("WorkEstHours")),
                        cursor.getString(cursor.getColumnIndex("Create")),
                        cursor.getString(cursor.getColumnIndex("LastUpdate")),
                        cursor.getString(cursor.getColumnIndex("actionLink")),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("depID"))),
                        cursor.getString(cursor.getColumnIndex("actionRef")),
                        cursor.getString(cursor.getColumnIndex("userCfname")),
                        cursor.getString(cursor.getColumnIndex("userClname")),
                        cursor.getString(cursor.getColumnIndex("userCemail")),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("userCtypeID"))),
                        cursor.getString(cursor.getColumnIndex("ownerCfname")),
                        cursor.getString(cursor.getColumnIndex("ownerClname")),
                        cursor.getString(cursor.getColumnIndex("ownerCemail")),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("ownerCtypeID"))),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("projectID"))),
                        cursor.getString(cursor.getColumnIndex("statusName")),
                        cursor.getString(cursor.getColumnIndex("PriorityName")),
                        cursor.getString(cursor.getColumnIndex("actionType")),
                        cursor.getString(cursor.getColumnIndex("actionSdate")),
                        cursor.getString(cursor.getColumnIndex("actionEdate")),
                        cursor.getString(cursor.getColumnIndex("WorkHoursM")),
                        cursor.getString(cursor.getColumnIndex("WorkEstHoursM")),
                        cursor.getString(cursor.getColumnIndex("actionPrice")),
                        cursor.getString(cursor.getColumnIndex("statusColor")),
                        cursor.getString(cursor.getColumnIndex("taskSummery")),
                        cursor.getString(cursor.getColumnIndex("projectSummery")),
                        cursor.getString(cursor.getColumnIndex("projectType")),
                        cursor.getString(cursor.getColumnIndex("actionNum")),
                        cursor.getString(cursor.getColumnIndex("actionFrom")),
                        cursor.getString(cursor.getColumnIndex("actionDays")),
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex("ParentActionID"))),
                        cursor.getString(cursor.getColumnIndex("remindertime")),
                        cursor.getString(cursor.getColumnIndex("Expr1")),
                        cursor.getString(cursor.getColumnIndex("projectDesc"))
                );
// Adding contact to list
                actions.add(action);
            } while (cursor.moveToNext());
        }
        cursor.close();
        }catch(Exception e){
            Helper h = new Helper();
            h.LogPrintExStackTrace(e);
        }
        return actions;
    }
    public List<Call> getCalls(String sortby) {
        List<Call> callList = new ArrayList<Call>();
// Select All Query
        String selectQuery ="";
            selectQuery = "SELECT * FROM mgnet_calls where 1=1 and statusid <> -1  " ;
        if (!sortby.trim().equals("")){
            selectQuery+=  sortby + "";
            //selectQuery+= "  order by " + sortby + "";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e("mytag","sql: " +selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Call c= new Call(
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallID"))),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("AID"))),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("CID"))),
                cursor.getString(cursor.getColumnIndex("CreateDate")),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("statusID"))),
                cursor.getString(cursor.getColumnIndex("CallPriority")),
                cursor.getString(cursor.getColumnIndex("subject")),
                cursor.getString(cursor.getColumnIndex("comments")),
                cursor.getString(cursor.getColumnIndex("CallUpdate")),
                cursor.getString(cursor.getColumnIndex("cntrctDate")),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("TechnicianID"))),
                cursor.getString(cursor.getColumnIndex("statusName")),
                cursor.getString(cursor.getColumnIndex("internalSN")),
                cursor.getString(cursor.getColumnIndex("Pmakat")),
                cursor.getString(cursor.getColumnIndex("Pname")),
                cursor.getString(cursor.getColumnIndex("contractID")),
                cursor.getString(cursor.getColumnIndex("Cphone")),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("OriginID"))),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("ProblemTypeID"))),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallTypeID"))),
                cursor.getString(cursor.getColumnIndex("priorityID")),
                cursor.getString(cursor.getColumnIndex("OriginName")),
                cursor.getString(cursor.getColumnIndex("problemTypeName")),
                cursor.getString(cursor.getColumnIndex("CallTypeName")),
                cursor.getString(cursor.getColumnIndex("Cname")),
                cursor.getString(cursor.getColumnIndex("Cemail")),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("contctCode"))),
                cursor.getString(cursor.getColumnIndex("callStartTime")),
                cursor.getString(cursor.getColumnIndex("callEndTime")),
                cursor.getString(cursor.getColumnIndex("Ccompany")),
                cursor.getString(cursor.getColumnIndex("Clocation")),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex("callOrder"))),
                cursor.getString(cursor.getColumnIndex("Caddress")),
                cursor.getString(cursor.getColumnIndex("Ccity")),
                cursor.getString(cursor.getColumnIndex("Ccomments")),
                cursor.getString(cursor.getColumnIndex("Cfname")),
                cursor.getString(cursor.getColumnIndex("Clname")),
                cursor.getString(cursor.getColumnIndex("techName")),
                cursor.getString(cursor.getColumnIndex("Aname")),
                cursor.getString(cursor.getColumnIndex("ContctName")),
                cursor.getString(cursor.getColumnIndex("ContctAddress")),
                cursor.getString(cursor.getColumnIndex("ContctCity")),
                cursor.getString(cursor.getColumnIndex("ContctCell")),
                cursor.getString(cursor.getColumnIndex("ContctPhone")),
                cursor.getString(cursor.getColumnIndex("ContctCity")),
                cursor.getString(cursor.getColumnIndex("Ccell")),
                cursor.getString(cursor.getColumnIndex("techColor")),
                cursor.getString(cursor.getColumnIndex("ContctCemail")),
                cursor.getString(cursor.getColumnIndex("CallParentID")),
                getState(cursor.getString(cursor.getColumnIndex("CallID"))),
                cursor.getString(cursor.getColumnIndex("sla"))
                );//cursor.getString(cursor.getColumnIndex("state")),
// Adding contact to list
                callList.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return callList;
    }
private String getState(String callid){
    Helper h = new Helper();
    try{
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "select count(*) from Calltime where callid=" + callid + " and ctq='-2'";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        //db.close();
        if(icount==2){
            return "work";
        }else if(icount==1){
            try{
                String count1 = "select count(*) from Calltime where callid=" + callid + " and ctq='-2' and CTcomment='work'";
                String res = "";
                res = getScalarByCountQuery(count1);
                Log.e("mytag","res:" +res);
                if (res == "1"){
                    return "work";
                }else{
                    return "ride";
                }
            }catch(Exception e){
                h.LogPrintExStackTrace(e);
                return "ride";
            }
        }else{
            return "null";
        }
    }catch(Exception e){
        h.LogPrintExStackTrace(e);
        return "null";
    }

}
public boolean getCallsCount() {
    String selectQuery = "SELECT count(callid) as count1 FROM mgnet_calls " ;
    boolean flag;
    try {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        flag = (cursor.getCount() > 0) ? true : false;
        db.close();
    }catch(Exception e){
        flag = false;
        Log.e("MYTAG","db + "+e.getMessage());
    }
    return flag;
}

    public int getFirstISAction() {
        String selectQuery = "SELECT * FROM IS_Actions order by actionID asc LIMIT 1" ;
        int flag;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            flag = Integer.valueOf(cursor.getString(cursor.getColumnIndex("actionID")));
            //flag = (cursor.getCount() > 0) ? true : false;
            //db.close ();
        }catch(Exception e){
            flag = 0;
            Log.e("MYTAG","db + "+e.getMessage());
        }

        IS_Action is = new IS_Action();
        String selectQuery2 = "SELECT * FROM IS_Actions" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery2, null);
        if (cursor.moveToFirst()) {
            do {
                is.setActionID( Integer.valueOf(cursor.getColumnIndex("actionID")));
                Log.e("mytag","list: "+String.valueOf(cursor.getColumnIndex("actionID")));
            } while (cursor.moveToNext());
        }
        // db.close();
        cursor.close();



        return flag;


    }
    public boolean IsExistCallID(int callid) {
        String selectQuery = "SELECT * FROM mgnet_calls where callid=" + callid + "" ;
        boolean flag;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            flag = (cursor.getCount() > 0) ? true : false;
            //db.close ();
        }catch(Exception e){
            flag = false;
            Log.e("MYTAG","db + "+e.getMessage());
        }
        return flag;
    }
public void addNewCall(Call call) {
    try{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CallID" , call.getCallID());
                values.put("AID", call.getAID());
                values.put("CID", call.getCID());
                values.put("CreateDate", call.getCreateDate());
               // values.put("EndDate", call.getEndDate());
                //values.put("CallEmail", call.getCallEmail());
                values.put("statusID", call.getStatusID());
                values.put("CallPriority", call.getCallPriority());
                values.put("subject", call.getSubject());
                values.put("comments", call.getComments());
                //values.put("IsClose", call.getIsClose());
                //values.put("IsRead", call.getIsRead());
                //values.put("CallType", call.getCallType());
                //values.put("CallReminderDate", call.getCallReminderDate());
                values.put("CallUpdate", call.getCallUpdate());
                //values.put("resolution", call.getResolution());
                values.put("cntrctDate", call.getCntrctDate());
                values.put("TechnicianID", call.getTechnicianID());
                values.put("statusName", call.getStatusName());
                //values.put("PcatID", call.getPcatID());
                values.put("internalSN", call.getInternalSN());
                values.put("Pmakat", call.getPmakat());
                values.put("Pname", call.getPname());
                values.put("contractID", call.getContractID());
                values.put("Cphone", call.getCphone());
                values.put("OriginID", call.getOriginID());
                values.put("ProblemTypeID" , call.getProblemTypeID());
                values.put("CallTypeID", call.getCallTypeID());
                values.put("priorityID", call.getPriorityID());
                values.put("OriginName", call.getOriginName());
                values.put("problemTypeName", call.getProblemTypeName());
                values.put("CallTypeName", call.getCallTypeName());
                values.put("Cname", call.getCname());
                values.put("Cemail", call.getCemail());
                values.put("contctCode", call.getContctCode());
                values.put("callStartTime", call.getCallStartTime());
                values.put("callEndTime", call.getCallEndTime());
                values.put("Ccompany", call.getCcompany());
                values.put("Clocation", call.getClocation());
                values.put("callOrder", call.getCallOrder());
                values.put("Caddress", call.getCaddress());
                values.put("Ccity", call.getCcity());
                values.put("Ccomments", call.getCcomments());
                values.put("Cfname", call.getCfname());
                values.put("Clname", call.getClname());
                values.put("techName", call.getTechName());
                values.put("Aname", call.getAname());
                values.put("ContctName", call.getContctName());
                values.put("ContctAddress", call.getContctAddress());
                values.put("ContctCity", call.getContctCity());
                values.put("ContctCell", call.getContctCell());
                values.put("ContctPhone", call.getContctPhone());
                values.put("Ccell", call.getCcell());
                values.put("techColor", call.getTechColor());
                values.put("ContctCemail", call.getContctCemail());
                values.put("CallParentID", call.getCallParentID());
                values.put("state", call.getState().trim());
                values.put("sla",  call.getSla().toString());
        // Inserting Row
        db.insert("mgnet_calls", null, values);
        // Closing database connection
        //db.close();

    }catch (Exception e){
        e.printStackTrace();
        Log.e("MYTAG"," db error1: " +e.getMessage());
    }

}
    public boolean deleteAllCalls() {
        boolean flag = true;
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete("mgnet_calls", null, null);
            //db.close();

        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }

//endregion

public Calltime getCalltimeByCallidAndAction(String callid,String action,String openClose){

    Calltime calltime = new Calltime();

    String openCloseCondition = "";
    if(openClose != ""){
        openCloseCondition = " and ctq='" + openClose + "' ";
    }

    try {
        String selectQuery = "";
        selectQuery = "SELECT * FROM Calltime where callid= '" + callid + "' and CTcomment= '" + action + "' " + openCloseCondition + " order by CTID desc LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0){
            calltime.setCTID(-1);
            return calltime;
        }
        if (cursor.moveToFirst()) {
            calltime.setCTID((cursor.getInt(cursor.getColumnIndex("CTID"))));
            calltime.setCallID((cursor.getInt(cursor.getColumnIndex("CallID"))));
            calltime.setCallStartTime((cursor.getString(cursor.getColumnIndex("CallStartTime"))));
            calltime.setCTcomment((cursor.getString(cursor.getColumnIndex("CTcomment"))));
            calltime.setMinute((cursor.getString(cursor.getColumnIndex("Minute"))));
            calltime.setCtq((cursor.getString(cursor.getColumnIndex("ctq"))));
        }
        cursor.close();
        return calltime;
    }
    catch(Exception e) {
        Helper h = new Helper();
        h.LogPrintExStackTrace(e);
        calltime.setCTID(-1);
        return calltime;
    }
}

public CallStatus getCallStatusByCallStatusName(String CallStatusName){

    CallStatus callStatus = new CallStatus();
    String selectQuery = "";
    selectQuery = "SELECT * FROM CallStatus where CallStatusName= '" + CallStatusName + "' ";
    SQLiteDatabase db = this.getReadableDatabase();
    try {
        Cursor cursor= db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            callStatus.setCallStatusID(Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallStatusID"))));
            callStatus.setCallStatusName( (cursor.getString(cursor.getColumnIndex("CallStatusName"))));
            callStatus.setCallStatusOrder( Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallStatusOrder"))));
        }
        cursor.close();
        return callStatus;
    }
    finally {

        return callStatus;
    }
}
    public void addCallStatus(CallStatus callStatus) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("CallStatusID", callStatus.getCallStatusID());
            values.put("CallStatusName", callStatus.getCallStatusName());
            values.put("CallStatusOrder", callStatus.getCallStatusOrder());
            db.insert(CallStatus, null, values);
            // Closing database connection
            //db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }

    }
    public void addISAction(IS_Action is_action) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("actionID",is_action.getActionID());
            values.put("taskID",is_action.getTaskID());
            values.put("statusID",is_action.getStatusID());
            values.put("ownerID",is_action.getOwnerID());
            values.put("userID",is_action.getUserID());
            values.put("depID",is_action.getDepID());
            values.put("userCtypeID",is_action.getUserCtypeID());
            values.put("ownerCtypeID",is_action.getOwnerCtypeID());
            values.put("projectID",is_action.getProjectID());
            values.put("ParentActionID",is_action.getParentActionID());
            values.put("actionDate",is_action.getActionDate());
            values.put("actionStartDate",is_action.getActionStartDate());
            values.put("actionDue",is_action.getActionDue());
            values.put("actionDesc",is_action.getActionDesc());
            values.put("comments",is_action.getComments());
            values.put("priorityID",is_action.getPriorityID());
            values.put("reminderID",is_action.getReminderID());
            values.put("WorkHours",is_action.getWorkHours());
            values.put("WorkEstHours",is_action.getWorkEstHours());
            values.put("[Create]",is_action.getCreate());
            values.put("LastUpdate",is_action.getLastUpdate());
            values.put("actionLink",is_action.getActionLink());
            values.put("actionRef",is_action.getActionRef());
            values.put("userCfname",is_action.getUserCfname());
            values.put("userClname",is_action.getUserClname());
            values.put("userCemail",is_action.getUserCemail());
            values.put("ownerCfname",is_action.getOwnerCfname());
            values.put("ownerClname",is_action.getOwnerClname());
            values.put("ownerCemail",is_action.getOwnerCemail());
            values.put("statusName",is_action.getStatusName());
            values.put("PriorityName",is_action.getPriorityName());
            values.put("actionType",is_action.getActionType());
            values.put("actionSdate",is_action.getActionSdate());
            values.put("actionEdate",is_action.getActionEdate());
            values.put("WorkHoursM",is_action.getWorkHoursM());
            values.put("WorkEstHoursM",is_action.getWorkEstHoursM());
            values.put("actionPrice",is_action.getActionPrice());
            values.put("statusColor",is_action.getStatusColor());
            values.put("taskSummery",is_action.getTaskSummery());
            values.put("projectSummery",is_action.getProjectSummery());
            values.put("projectType",is_action.getProjectType());
            values.put("actionNum",is_action.getActionNum());
            values.put("actionFrom",is_action.getActionFrom());
            values.put("actionDays",is_action.getActionDays());
            values.put("remindertime",is_action.getRemindertime());
            values.put("Expr1",is_action.getExpr1());
            values.put("projectDesc",is_action.getProjectDesc());

            db.insert("IS_Actions", null, values);
            Log.e("MYTAG","added!!" + is_action.toString());
            // Closing database connection
            //db.close();
        }catch (Exception e){
            //e.printStackTrace();
            Helper h = new Helper();
            h.LogPrintExStackTrace(e);
            Log.e("MYTAG",e.getMessage());
            return;
        }

    }


    //region CallStatus
    public List<CallStatus> getCallStausList() {
        List<CallStatus> statusList = new ArrayList<CallStatus>();
// Select All Query
        String selectQuery = "";
            selectQuery = "SELECT * FROM CallStatus";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CallStatus cp= new CallStatus(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2)
                );
                statusList.add(cp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return statusList;
    }
    public boolean deleteCallStatuses() {
        boolean flag = true;
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(CallStatus, null, null);
            //db.close();

        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }
    //endregion

    public boolean isEmpty(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_CONTROL_PANEL;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        //db.close();
        if(icount>0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean mgnet_items_isEmpty(String parameter){

        boolean flag = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String count = "";
            if (parameter.equals("all")){
                count = "SELECT count(Pname) FROM mgnet_items";
            }else{
                count = "SELECT count(Pname) FROM mgnet_client_items";

            }
            Cursor mcursor = db.rawQuery(count, null);
            mcursor.moveToFirst();
            int icount = mcursor.getInt(0);
            //db.close();
            if(icount>0){
                flag = false;
            }
            else{
                flag = true;
            }
            mcursor.close();
        }catch(Exception ex){
            Log.e("MYTAG",ex.getMessage());
        }
        return  flag;
    }
    public List<Order> get_mgnet_items_by_Pname(String pname,String allORclient) {
        List<Order> orderList = new ArrayList<Order>();
// Select All Query
        String selectQuery = "";
        if (allORclient.equals("all")) {
            selectQuery = "SELECT * FROM mgnet_items where Pname like '%" + pname + "%' ";

        } else{
            selectQuery = "SELECT * FROM mgnet_client_items where Pname like '%" +pname+ "%' " ;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order cp= new Order(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)

                );

// Adding contact to list
                orderList.add(cp);
            } while (cursor.moveToNext());
        }
        //db.close();
        return orderList;
    }
    public List<Order> get_mgnet_items(String allORclient) {
        Helper h = new Helper();
        List<Order> orderList = new ArrayList<Order>();
        try{

// Select All Query
            String selectQuery ="";
            if (allORclient.equals("all")){
                selectQuery = "SELECT * FROM mgnet_items " ;
            }else{
                selectQuery = "SELECT * FROM mgnet_client_items " ;
            }
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Order cp= new Order(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)

                    );

// Adding contact to list
                    orderList.add(cp);
                } while (cursor.moveToNext());
            }
            //db.close();
            return orderList;
        }catch (Exception e){

            h.LogPrintExStackTrace(e);
        }
        return orderList;
    }

    public Order get_mgnet_item_by_pmakat(String pmakat) {
        List<Order> orderList = new ArrayList<Order>();
// Select All Query
        String selectQuery = "SELECT top 1 * FROM mgnet_items where Pmakat like '%" + pmakat + "%'" ;
        Order cp = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            Log.e("MYTAG","db step 1 ");

//
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Log.e("MYTAG","db step 2 ");
                cp = new Order(
                        is_empty_cursor(cursor.getString(0)),
                        is_empty_cursor(cursor.getString(1)),
                        is_empty_cursor(cursor.getString(2)),
                        is_empty_cursor(cursor.getString(3)));
            }
            db.close();
        }catch(Exception e){
            Log.e("MYTAG","db + "+e.getMessage());
        }
        return cp;
    }
    public String is_empty_cursor(String s){
        if (isEmpty(s)) {
            return "";
        }else{
            return s;

        }
    }
    public boolean delete_from_mgnet_items(String allORclient) {
        boolean flag = true;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            if (allORclient.equals("all")){
                db.delete("mgnet_items", null, null);
            }else{
                db.delete("mgnet_client_items", null, null);

            }

            //db.insert("mgnet_items", null, values);
            db.close(); // Closing database connection
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }
        return flag;
    }


    public void add_mgnet_items(String pname,String Pmakat,String Pprice,String Poprice,String allORclient) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Pname", pname);
            values.put("Pmakat", Pmakat);
            values.put("Pprice", Pprice);
            values.put("Poprice", Poprice);
            // Inserting Row
            if (allORclient.equals("all")){
                db.insert("mgnet_items", null, values);
            }else{
                db.insert("mgnet_client_items", null, values);
            }

            db.close(); // Closing database connection
        }catch (Exception e){
            e.printStackTrace();
            Log.e("MYTAG",e.getMessage());
        }

    }
    public Call getCallDetailsByCallID(int callid){

        //Call call = new Call(Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallID"))), Integer.valueOf(cursor.getString(cursor.getColumnIndex("AID"))), Integer.valueOf(cursor.getString(cursor.getColumnIndex("CID"))), cursor.getString(cursor.getColumnIndex("CreateDate")), Integer.valueOf(cursor.getString(cursor.getColumnIndex("statusID"))), cursor.getString(cursor.getColumnIndex("CallPriority")), cursor.getString(cursor.getColumnIndex("subject")), cursor.getString(cursor.getColumnIndex("comments")), cursor.getString(cursor.getColumnIndex("CallUpdate")), cursor.getString(cursor.getColumnIndex("cntrctDate")), Integer.valueOf(cursor.getString(cursor.getColumnIndex("TechnicianID"))), cursor.getString(cursor.getColumnIndex("statusName")), cursor.getString(cursor.getColumnIndex("internalSN")), cursor.getString(cursor.getColumnIndex("Pmakat")), cursor.getString(cursor.getColumnIndex("Pname")), cursor.getString(cursor.getColumnIndex("contractID")), cursor.getString(cursor.getColumnIndex("Cphone")), Integer.valueOf(cursor.getString(cursor.getColumnIndex("OriginID"))), Integer.valueOf(cursor.getString(cursor.getColumnIndex("ProblemTypeID"))), Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallTypeID"))), cursor.getString(cursor.getColumnIndex("priorityID")), cursor.getString(cursor.getColumnIndex("OriginName")), cursor.getString(cursor.getColumnIndex("problemTypeName")), cursor.getString(cursor.getColumnIndex("CallTypeName")), cursor.getString(cursor.getColumnIndex("Cname")), cursor.getString(cursor.getColumnIndex("Cemail")), Integer.valueOf(cursor.getString(cursor.getColumnIndex("contctCode"))), cursor.getString(cursor.getColumnIndex("callStartTime")), cursor.getString(cursor.getColumnIndex("callEndTime")), cursor.getString(cursor.getColumnIndex("Ccompany")), cursor.getString(cursor.getColumnIndex("Clocation")), Integer.valueOf(cursor.getString(cursor.getColumnIndex("callOrder"))), cursor.getString(cursor.getColumnIndex("Caddress")), cursor.getString(cursor.getColumnIndex("Ccity")), cursor.getString(cursor.getColumnIndex("Ccomments")), cursor.getString(cursor.getColumnIndex("Cfname")), cursor.getString(cursor.getColumnIndex("Clname")), cursor.getString(cursor.getColumnIndex("techName")), cursor.getString(cursor.getColumnIndex("Aname")), cursor.getString(cursor.getColumnIndex("ContctName")), cursor.getString(cursor.getColumnIndex("ContctAddress")), cursor.getString(cursor.getColumnIndex("ContctCity")), cursor.getString(cursor.getColumnIndex("ContctCell")), cursor.getString(cursor.getColumnIndex("ContctPhone")), cursor.getString(cursor.getColumnIndex("ContctCity")), cursor.getString(cursor.getColumnIndex("Ccell")), cursor.getString(cursor.getColumnIndex("techColor")), cursor.getString(cursor.getColumnIndex("ContctCemail")), cursor.getString(cursor.getColumnIndex("CallParentID")));
        Call call = new Call();
        String selectQuery = "";
        selectQuery = "SELECT * FROM mgnet_calls where callid= " + callid + " ";
        SQLiteDatabase db = this.getReadableDatabase();


        try {
            Cursor cursor= db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                call.setCallID(Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallID"))));
                call.setAID( Integer.valueOf(cursor.getString(cursor.getColumnIndex("AID"))));
                call.setCID( Integer.valueOf(cursor.getString(cursor.getColumnIndex("CID"))));
                call.setCreateDate( (cursor.getString(cursor.getColumnIndex("CreateDate"))));
                call.setStatusID( Integer.valueOf(cursor.getString(cursor.getColumnIndex("statusID"))));
                call.setCallPriority( (cursor.getString(cursor.getColumnIndex("CallPriority"))));
                call.setSubject( (cursor.getString(cursor.getColumnIndex("subject"))));
                call.setComments( (cursor.getString(cursor.getColumnIndex("comments"))));
                call.setCallUpdate( (cursor.getString(cursor.getColumnIndex("CallUpdate"))));
                call.setCntrctDate( (cursor.getString(cursor.getColumnIndex("cntrctDate"))));
                call.setTechnicianID( Integer.valueOf(cursor.getString(cursor.getColumnIndex("TechnicianID"))));
                call.setStatusName( (cursor.getString(cursor.getColumnIndex("statusName"))));
                call.setInternalSN( (cursor.getString(cursor.getColumnIndex("internalSN"))));
                call.setPmakat( (cursor.getString(cursor.getColumnIndex("Pmakat"))));
                call.setPname( (cursor.getString(cursor.getColumnIndex("Pname"))));
                call.setContractID( (cursor.getString(cursor.getColumnIndex("contractID"))));
                call.setCphone( (cursor.getString(cursor.getColumnIndex("Cphone"))));
                call.setOriginID( Integer.valueOf(cursor.getString(cursor.getColumnIndex("OriginID"))));
                call.setProblemTypeID( Integer.valueOf(cursor.getString(cursor.getColumnIndex("ProblemTypeID"))));
                call.setCallTypeID( Integer.valueOf(cursor.getString(cursor.getColumnIndex("CallTypeID"))));
                call.setPriorityID( (cursor.getString(cursor.getColumnIndex("priorityID"))));
                call.setOriginName( (cursor.getString(cursor.getColumnIndex("OriginName"))));
                call.setProblemTypeName( (cursor.getString(cursor.getColumnIndex("problemTypeName"))));
                call.setCallTypeName( (cursor.getString(cursor.getColumnIndex("CallTypeName"))));
                call.setCname( (cursor.getString(cursor.getColumnIndex("Cname"))));

                call.setCemail( (cursor.getString(cursor.getColumnIndex("Cemail"))));
                call.setContctCode( Integer.valueOf(cursor.getString(cursor.getColumnIndex("contctCode"))));
                call.setCallStartTime( (cursor.getString(cursor.getColumnIndex("callStartTime"))));
                call.setCallEndTime( (cursor.getString(cursor.getColumnIndex("callEndTime"))));
                call.setCcompany( (cursor.getString(cursor.getColumnIndex("Ccompany"))));
                call.setClocation( (cursor.getString(cursor.getColumnIndex("Clocation"))));
                call.setCallOrder( Integer.valueOf(cursor.getString(cursor.getColumnIndex("callOrder"))));

                call.setCaddress( (cursor.getString(cursor.getColumnIndex("Caddress"))));
                call.setCcity( (cursor.getString(cursor.getColumnIndex("Ccity"))));
                call.setCcomments( (cursor.getString(cursor.getColumnIndex("Ccomments"))));
                call.setCfname( (cursor.getString(cursor.getColumnIndex("Cfname"))));
                call.setClname( (cursor.getString(cursor.getColumnIndex("Clname"))));

                call.setTechName( (cursor.getString(cursor.getColumnIndex("techName"))));
                call.setAname( (cursor.getString(cursor.getColumnIndex("Aname"))));
                call.setContctName( (cursor.getString(cursor.getColumnIndex("ContctName"))));
                call.setContctAddress( (cursor.getString(cursor.getColumnIndex("ContctAddress"))));
                call.setContctCity( (cursor.getString(cursor.getColumnIndex("ContctCity"))));

                call.setContctCell( (cursor.getString(cursor.getColumnIndex("ContctCell"))));
                call.setContctPhone( (cursor.getString(cursor.getColumnIndex("ContctPhone"))));
                call.setContctCity( (cursor.getString(cursor.getColumnIndex("ContctCity"))));
                call.setCcell( (cursor.getString(cursor.getColumnIndex("Ccell"))));
                call.setTechColor( (cursor.getString(cursor.getColumnIndex("techColor"))));
                call.setContctCemail( (cursor.getString(cursor.getColumnIndex("ContctCemail"))));
                call.setCallParentID( (cursor.getString(cursor.getColumnIndex("CallParentID"))));
                call.setState( (cursor.getString(cursor.getColumnIndex("state"))));
                call.setSla( (cursor.getString(cursor.getColumnIndex("sla"))));
            }
            cursor.close();
            return call;
        }
        finally {
           // if (cursor != null) {
            //    cursor.close();
                //db.close();
                return call;
            //}
        }


        //db.close();
        //eturn call;
    }

    public void addControlPanel(String key,String val) {

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY, key);
            values.put(VALUE, val);
            values.put(DESCRIPTION, "");
            // Inserting Row
            db.insert(TABLE_CONTROL_PANEL, null, values);
           // db.close(); // Closing database connection
        }catch (Exception e){
            Helper h = new Helper();
            h.LogPrintExStackTrace(e);
            //e.printStackTrace();
        }

    }

    public void addMessage(Message message) {

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(MsgID , message.getMsgID());
            values.put(MsgSUBJECT, message.getMsgSubject());
            values.put(MsgCOMMENT, message.getMsgComment());
            values.put(MsgURL , message.getMsgUrl());
            values.put(MsgDATE  , message.getMsgDate());
            values.put(MsgREAD  , message.getMsgRead());
            values.put(MsgTYPE  , message.getMsgType());

            // Inserting Row
            db.insert(TABLE_MESSAGES, null, values);
           // db.close(); // Closing database connection
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    //if isEmpty
    public boolean verification(String _username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int count = -1;
        Cursor c = null;
        try {
            String query = "SELECT COUNT(*) FROM "
                    + TABLE_CONTROL_PANEL + " WHERE " + KEY + " = ?";
            c = db.rawQuery(query, new String[] {_username});
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            return count > 0;
        }
        finally {
            if (c != null) {
                c.close();
                //db.close();
            }
        }
    }

    public boolean checkIfKeyExistsCP(String stringKey) {
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = null;
            String sql ="select count(*) as mycount from " + TABLE_CONTROL_PANEL + " WHERE " + KEY + " = '" + stringKey +"'";
            cursor= db.rawQuery(sql,null);
            //Log("Cursor Count : " + cursor.getCount());
            Log.e("mytag","cursor.getCount():" +(cursor.getColumnIndex("mycount")));
            if(Integer.valueOf(cursor.getColumnIndex("mycount")) >0){
                //PID Found

                cursor.close();
                return true;
            }else{
                cursor.close();
                //PID Not Found
                return false;
            }
        }catch(Exception e){
            return false;
        }

    }


    public ControlPanel getControlPanel(String key) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT  * FROM " + TABLE_CONTROL_PANEL + " WHERE "
               + KEY + " = '" + key+"'";
        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        ControlPanel cp = new ControlPanel();
        //cp.setId(c.getInt(c.getColumnIndex(ID)));
        cp.setKey((c.getString(c.getColumnIndex(KEY))));
        cp.setValue(c.getString(c.getColumnIndex(VALUE)));

        db.close();
        return cp;
    }

    public Message getMsgByKey(String msgID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT  * FROM " + TABLE_MESSAGES + " WHERE "
                + MsgID + " = '" + msgID+"'";
        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        Message cp = new Message();
        //cp.setId(c.getInt(c.getColumnIndex(ID)));
        cp.setMsgID     (c.getString(c.getColumnIndex(MsgID)));
        cp.setMsgSubject(c.getString(c.getColumnIndex(MsgSUBJECT)));
        cp.setMsgComment(c.getString(c.getColumnIndex(MsgCOMMENT)));
        cp.setMsgUrl    (c.getString(c.getColumnIndex(MsgURL)));
        cp.setMsgDate   (c.getString(c.getColumnIndex(MsgDATE)));
        cp.setMsgRead   (c.getString(c.getColumnIndex(MsgREAD)));
        cp.setMsgType   (c.getString(c.getColumnIndex(MsgTYPE)));

        db.close();
        return cp;
    }

    public String getValueByKey(String key) {

        SQLiteDatabase db = this.getReadableDatabase();
        ControlPanel cp = new ControlPanel();
        String query = "SELECT  * FROM " + TABLE_CONTROL_PANEL + " WHERE "
                + KEY + " = '" + key+"'";
        try{
            Cursor c = db.rawQuery(query, null);

            if (c != null)
                c.moveToFirst();
            try {

                //cp.setId(c.getInt(c.getColumnIndex(ID)));
                cp.setKey((c.getString(c.getColumnIndex(KEY))));
                cp.setValue(c.getString(c.getColumnIndex(VALUE)));
                c.close();
                //db.close();
                return cp.getValue();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            Log.e("mytag",e.getMessage());
            Log.e("mytag",e.getStackTrace().toString());
        }

        db.close();
        return "";
    }

    public List<ControlPanel> getAllKeysAndValues() {
        List<ControlPanel> control_panel_list = new ArrayList<ControlPanel>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTROL_PANEL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ControlPanel cp= new ControlPanel();
                //cp.setId(Integer.parseInt(cursor.getString(0)));
                cp.setKey(cursor.getString(0));
                cp.setValue(cursor.getString(1));
                cp.setDescription(cursor.getString(2));
// Adding contact to list
                control_panel_list.add(cp);
            } while (cursor.moveToNext());
        }
       // db.close();
        cursor.close();
        return control_panel_list;
    }

    //// TODO: 01/09/2016 fix it 
    public List<Message> getAllMessages() {
        List<Message> messages_list = new ArrayList<Message>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MESSAGES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Message cp= new Message();
                cp.setMsgID(cursor.getString(0));
                cp.setMsgSubject(cursor.getString(1));
                cp.setMsgComment(cursor.getString(2));
                cp.setMsgUrl(cursor.getString(3));
                cp.setMsgDate(cursor.getString(4));
                cp.setMsgRead(cursor.getString(5));
                cp.setMsgType(cursor.getString(6));
                        //cp.setValue(cursor.getString(1));
                //cp.setDescription(cursor.getString(2));
// Adding contact to list
                messages_list.add(cp);
            } while (cursor.moveToNext());
        }
        //db.close();
        cursor.close();
        return messages_list;
    }

    public void updateValue(String key,String val) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(VALUE, val);
            //values.put(DESCRIPTION, "");
            db.update(TABLE_CONTROL_PANEL, values, KEY + " = '"+key+"'", null);
            //db.close();
        }catch(Exception e){
            Helper h = new Helper();
            h.LogPrintExStackTrace(e);
            Toast.makeText(mCtx, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }



    public void resetURL(ControlPanel cp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY, cp.getKey());
        values.put(VALUE, cp.getValue());
// updating row
        db.update(TABLE_CONTROL_PANEL, values, ID + " = " + cp.getId(), null);
        db.close();
    }


    public void deleteRowByKey(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTROL_PANEL, KEY + " = '" + key+"'", null);
        //db.execSQL("delete from "+ TABLE_CONTROL_PANEL);
        db.close();
    }
    public void deleteRowByMsgID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MESSAGES, MsgID + " = '" + id+"'", null);
        //db.execSQL("delete from "+ TABLE_CONTROL_PANEL);
        db.close();
    }


}


