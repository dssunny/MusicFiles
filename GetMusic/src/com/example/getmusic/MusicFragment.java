package com.example.getmusic;

import java.util.ArrayList;



import android.content.Context;
import android.content.MutableContextWrapper;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MusicFragment extends Fragment {
	
	PopupWindow objPopUpWindowMusicFileDetails;
	
	Context objContext;
	ListView lstView;
	MusicAdapter objMusicAdapter;
	ArrayList<String> alMusicFiles;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		objContext=getActivity().getApplicationContext();
		return inflater.inflate(R.layout.list_music, container, false);
			
	}
	
	
	
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		alMusicFiles=MainActivity.arrMusicList;
		lstView = (ListView)getView().findViewById(R.id.musicList_lstview);
		objMusicAdapter=new MusicAdapter(objContext, alMusicFiles);
		lstView.setAdapter(objMusicAdapter);
		
		lstView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String selectedText=alMusicFiles.get(position);
				
				 objPopUpWindowMusicFileDetails = popupWindowMusicFileDetails(selectedText);
				 
				 objPopUpWindowMusicFileDetails.showAsDropDown(view, -5, 0);
				 
				
			}
		});
		
		
		   
		

		}
	
	
	 public PopupWindow popupWindowMusicFileDetails(String selectedText) {

	        // initialize a pop up window type
	        PopupWindow popupWindow = new PopupWindow(getActivity());

	        
	        //show selected text 
	        TextView txtPopup=new TextView(getActivity());
	        
	        txtPopup.setText(selectedText);
	        txtPopup.setTextColor(Color.WHITE);
	        
	        popupWindow.setFocusable(true);
	        popupWindow.setWidth(400);
	        popupWindow.setHeight(70);
	        
	     // set the list view as pop up window content
	        popupWindow.setContentView(txtPopup);
			        
			        // dismiss the pop up
			       
	        return popupWindow;
	    }

}


