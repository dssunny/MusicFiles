package com.example.getmusic;

import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class ClsLauncher extends FragmentActivity {

	Cursor sdCardMusicFileCursor, phoneMemoryMusicFileCursor;
	String musicPath[], musicTitle[];
	int count;
	FragmentTransaction transaction;
	public static ArrayList<String> arrMusicList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try{
			// get the music file names of sd card
			sdCardMusicFileCursor = getContentResolver().query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
					null);

			// get the music file names of phone memory 
			phoneMemoryMusicFileCursor = getContentResolver().query(
					MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null, null, null,
					null);
			musicPath = new String[sdCardMusicFileCursor.getCount()];
			musicTitle = new String[sdCardMusicFileCursor.getCount()];

			arrMusicList = new ArrayList<String>();
			count = sdCardMusicFileCursor.getCount();
			sdCardMusicFileCursor.moveToFirst();
			do {
				musicPath[sdCardMusicFileCursor.getPosition()] = sdCardMusicFileCursor
						.getString(sdCardMusicFileCursor
								.getColumnIndexOrThrow(MediaStore.Audio.Media.IS_MUSIC));
				if (sdCardMusicFileCursor
						.getString(
								sdCardMusicFileCursor
								.getColumnIndexOrThrow(MediaStore.Audio.Media.IS_MUSIC))
								.equals("1")) {
					musicTitle[sdCardMusicFileCursor.getPosition()] = sdCardMusicFileCursor
							.getString(sdCardMusicFileCursor
									.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
					arrMusicList
					.add(sdCardMusicFileCursor.getString(sdCardMusicFileCursor
							.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
				}
				// Log.i(musicPath[cursor.getPosition()],musicTitle[cursor.getPosition()]);

			} while (sdCardMusicFileCursor.moveToNext());

			// arrMusicList=new ArrayList<String>(Arrays.asList(musicTitle));

			/*
			 * arrayAdapter= new
			 * ArrayAdapter(this,R.layout.simple_list_item_1,musicTitle);
			 * MusicList.setAdapter(arrayAdapter);
			 */

			phoneMemoryMusicFileCursor.moveToFirst();
			do {

				if (phoneMemoryMusicFileCursor
						.getString(
								phoneMemoryMusicFileCursor
								.getColumnIndexOrThrow(MediaStore.Audio.Media.IS_MUSIC))
								.equals("1")) {

					arrMusicList
					.add(phoneMemoryMusicFileCursor.getString(phoneMemoryMusicFileCursor
							.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

				}
				// Log.i(musicPath[cursor.getPosition()],musicTitle[cursor.getPosition()]);

			} while (phoneMemoryMusicFileCursor.moveToNext());

			sdCardMusicFileCursor.close();
			phoneMemoryMusicFileCursor.close();

			/* MusicFragment music=new MusicFragment(); */
			if (findViewById(R.id.frame_main_screen1) != null) {

				// However, if we're being restored from a previous state,
				// then we don't need to do anything and should return or else
				// we could end up with overlapping fragments.
				if (savedInstanceState != null) {
					return;
				}
				// ActivityFragment Af=new ActivityFragment();

				// UpcomingFragment up=new UpcomingFragment();
				// getSupportFragmentManager().beginTransaction()
				// .add(R.id.frame_main_screen, ).commit();

				ClsMusicFileListFragment music = new ClsMusicFileListFragment();
				getSupportFragmentManager().beginTransaction()
				.add(R.id.frame_main_screen1, music).commit();

				FragmentTransaction transaction = getSupportFragmentManager()
						.beginTransaction();
				transaction.replace(R.id.frame_main_screen1, music);
				transaction.addToBackStack(null);
			}

		}catch(Exception e){

			//e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
