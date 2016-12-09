package com.t3h.bomb.manager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class SoundManager {
	public static SoundManager instance;

	public static final String MENU = "/res/sounds/menu.wav";
	public static final String PLAY_GAME = "/res/sounds/play_game.mid";
	public static final String BOMB = "/res/sounds/new_bomb.wav";
	public static final String BOMBER_DIE = "/res/sounds/bomber_die.wav";
	public static final String MONSTER_DIE = "/res/sounds/monster_die.wav";
	public static final String BONG_BANG = "/res/sounds/bomb_bang.wav";
	public static final String ITEM = "/res/sounds/item.wav";
	public static final String WIN = "/res/sounds/win.wav";
	public static final String LOSE = "/res/sounds/lose.mid";
	private HashMap<String, AudioClip> audioMap;

	public SoundManager() {
		audioMap = new HashMap<>();
		loadAllAudio();
	}

	public static SoundManager getInstance() {
		if (instance == null) {
			instance = new SoundManager();
		}
		return instance;
	}

	public void loadAllAudio() {
		putAudio(MENU);
		putAudio(PLAY_GAME);
		putAudio(BOMB);
		putAudio(MONSTER_DIE);
		putAudio(BOMBER_DIE);
		putAudio(BONG_BANG);
		putAudio(ITEM);
		putAudio(WIN);
		putAudio(LOSE);
	}

	public void stop() {
		getAudio(MENU).stop();
		getAudio(PLAY_GAME).stop();
		getAudio(BOMB).stop();
		getAudio(BONG_BANG).stop();
		getAudio(WIN).stop();
		getAudio(LOSE).stop();
	}

	public void putAudio(String name) {
		AudioClip auClip = Applet.newAudioClip(SoundManager.class.getResource(name));
		audioMap.put(name, auClip);
	}

	public AudioClip getAudio(String name) {
		return audioMap.get(name);
	}
}
