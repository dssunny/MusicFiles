package com.example.getmusic;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter{
	
	ArrayList<String> alMusicFiles;
	Context context;
	
	// Constructor Daily Adapter
		public MusicAdapter(Context context,ArrayList<String> arrListMusic) {
			this.context = context;
			this.alMusicFiles = arrListMusic;
			//this.txtLName = txtLName;
			// this.arrDesignation=arrDesignation;
		}	
	
		
		
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View gridView = null;
			if (convertView != null) {
				gridView=convertView;
			} else {
				gridView = new View(context);

				// grid view object hold the layout of daily_text.xml file
				gridView = inflater.inflate(R.layout.list_item, null);	
			}

				TextView musicNameTxtView = (TextView) gridView
						.findViewById(R.id.musicFileName_txt);

				
				musicNameTxtView.setText(alMusicFiles.get(position));
				
				musicNameTxtView.setTextColor(Color.BLACK);
				musicNameTxtView.setTextSize(20);
				musicNameTxtView.setHeight(70);
				//				
				gridView.setLayoutParams(new ListView.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				

			return gridView;

		}


	

		// method count list number
		@Override
		public int getCount() {
			return alMusicFiles.size();
		}

		@Override
		// method for get item
		public Object getItem(int position) {

			return null;

		}

		// method get item id
		@Override
		public long getItemId(int position) {

			return 0;

		}


}
