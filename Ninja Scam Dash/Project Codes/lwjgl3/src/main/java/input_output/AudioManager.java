package input_output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static AudioManager instance;
    private final Map<String, Sound> soundMap;
    private final Map<String, Long> playingSounds;

    private AudioManager() {
        soundMap = new HashMap<>();
        playingSounds = new HashMap<>();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void playSound(String filePath) {
        if (playingSounds.containsKey(filePath)) return; // Prevent duplicate playing

        Sound sound = soundMap.computeIfAbsent(filePath, key -> Gdx.audio.newSound(Gdx.files.internal(key)));
        long id = sound.play();
        playingSounds.put(filePath, id);
    }

    public void playSoundOnLoop(String filePath) {
        if (playingSounds.containsKey(filePath)) return; // Prevent duplicate loops
        Sound sound = soundMap.computeIfAbsent(filePath, key -> Gdx.audio.newSound(Gdx.files.internal(key)));
        long id = sound.loop();
        playingSounds.put(filePath, id);
    }
    
    public void playUISound(String filePath) {
        Sound sound = soundMap.computeIfAbsent(filePath, key -> Gdx.audio.newSound(Gdx.files.internal(key)));
        sound.play(); // Play without tracking the ID
    }
    
    public void stopSound(String filePath) {
        if (playingSounds.containsKey(filePath)) {
            soundMap.get(filePath).stop(playingSounds.get(filePath));
            playingSounds.remove(filePath);
        }
    }

    public boolean isPlaying(String filePath) {
        return playingSounds.containsKey(filePath);
    }


    public void dispose() {
        for (Sound sound : soundMap.values()) {
            sound.dispose();
        }
        soundMap.clear();
        playingSounds.clear();
    }
}
