package com.example.dknng.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ChatWindow extends Activity {

    private ListView listView;
    private EditText text;
    private Button chatView;
    private ArrayList<String> list;
    private EditText textInput;
    ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = findViewById(R.id.listview);
        text = findViewById(R.id.text);
        chatView = findViewById(R.id.chatview);
        textInput = findViewById(R.id.text);

        list = new ArrayList<>();

        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               list.add(text.getText().toString());
               messageAdapter.notifyDataSetChanged();
               textInput.setText("");
            }
        });

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
}
