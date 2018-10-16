package com.example.dknng.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ChatWindow extends Activity {

    private ListView listView;
    private EditText text;
    private Button chatView;
    private ArrayList<String> list;
    private EditText textInput;
    ChatAdapter messageAdapter;
    protected static final String ACTIVITY_NAME = "ChatWindow";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = findViewById(R.id.listview);
        text = findViewById(R.id.text);
        chatView = findViewById(R.id.chatview);
        textInput = findViewById(R.id.text);

        list = new ArrayList<>();
//open data
       ChatDatabaseHelper dbOpener = new ChatDatabaseHelper(this);//helper object
        final SQLiteDatabase db = dbOpener.getWritableDatabase();

        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

//Create a query
        String[] columns = {ChatDatabaseHelper.KEY_ID, ChatDatabaseHelper.KEY_MESSAGE};
        Cursor results = db.query(ChatDatabaseHelper.TABLE_NAME, columns,
                null,null,null,null, null);
        results.moveToFirst();//read the first row

        //message that you retrieve from the cursor object
        while(!results.isAfterLast()){
            String newMessage =  results.getString(results.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            list.add(newMessage);
            Log.i(ACTIVITY_NAME,"SQL MESSAGE:" + newMessage);
            results.moveToNext();
        }

    //Send button
        chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues newRow = new ContentValues();
                newRow.put(ChatDatabaseHelper.KEY_MESSAGE,text.getText().toString()); //all columns have a value
                list.add(text.getText().toString());
                db.insert(ChatDatabaseHelper.TABLE_NAME,"ReplacementValue", newRow);
                 textInput.setText("");
                messageAdapter.notifyDataSetChanged();//data has changed
            }
        });


      /* int numResults = results.getCount();
       int idColumn = results.getColumnIndex(ChatDatabaseHelper.KEY_ID);
       int messColumn = results.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE);*/


        //info message about the cursor
        Log.i(ACTIVITY_NAME,"Cursor's column count=" + results.getColumnCount());

        for(int i = 0; i <results.getColumnCount(); i++){
            String columnName = results.getColumnName(i);
            Log.i(ACTIVITY_NAME,"Column name:" + columnName);
        }

       /*SimpleCursorAdapter resultsAdapter = new SimpleCursorAdapter(this, R.layout.chat_row_outgoing, results,
                new String[] {"KEY_ID","KEY_MESSAGE"},
                new int[]{R.id.text,R.id.message_text},0);
        SimpleCursorAdapter resultsAdapter2 = new SimpleCursorAdapter(this, R.layout.chat_row_incoming, results,
                new String[] {"KEY_ID","KEY_MESSAGE"},
                new int[]{R.id.text,R.id.message_text},0);
        listView.setAdapter(resultsAdapter);
        listView.setAdapter(resultsAdapter2);*/
    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx){
            super( ctx,0);
        }
        public int getCount(){
            return list.size();
        }

        public String getItem(int position){
            return list.get(position);
        }
        //step 9
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position % 2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            }else{
                result = inflater.inflate(R.layout.chat_row_outgoing,null);
            }

            TextView message = result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
        public long getItemId(int position){
            return position;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy");
    }
}
