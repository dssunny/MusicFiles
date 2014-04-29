package com.example.getmusic;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ClsMusicFileListFragment extends Fragment {

	PopupWindow objPopUpWindowMusicFileDetails;

	Context objContext;
	ListView lstView;
	ClsMusicFilesAdapter objMusicAdapter;
	ArrayList<String> alMusicFiles;
	String strYouSelectedTxt = "You Selected :";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		objContext = getActivity().getApplicationContext();
		return inflater.inflate(R.layout.list_music, container, false);

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		alMusicFiles = ClsLauncher.arrMusicList;
		lstView = (ListView) getView().findViewById(R.id.musicList_lstview);
		objMusicAdapter = new ClsMusicFilesAdapter(objContext, alMusicFiles);
		lstView.setAdapter(objMusicAdapter);

		lstView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String selectedText = alMusicFiles.get(position);

				objPopUpWindowMusicFileDetails = popupWindowMusicFileDetails(selectedText);

				objPopUpWindowMusicFileDetails.showAsDropDown(view, -5, 0);

			}
		});

	}

	// shows a pop up window on the list item

	public PopupWindow popupWindowMusicFileDetails(String selectedText) {

		// initialize a pop up window type
		PopupWindow popupWindow = new PopupWindow(getActivity());

		// show selected text
		TextView txtPopup = new TextView(getActivity());

		txtPopup.setText(strYouSelectedTxt + selectedText);
		txtPopup.setTextColor(Color.WHITE);

		popupWindow.setFocusable(true);
		popupWindow.setWidth(500);
		popupWindow.setHeight(100);

		// set the list view as pop up window content
		popupWindow.setContentView(txtPopup);

		// dismiss the pop up

		return popupWindow;
	}

}
