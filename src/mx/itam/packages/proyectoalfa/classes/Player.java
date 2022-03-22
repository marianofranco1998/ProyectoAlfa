package mx.itam.packages.proyectoalfa.classes;

import java.io.Serializable;

public class Player implements Serializable {
    private String user;
    private String ip;
    private boolean playing;
    private int score;

    public Player(String user, String ip) {
        this.user = user;
        this.ip = ip;
        this.playing = false;
        this.score = 0;
    }

    public Player(String user, String ip, boolean b, int i) {
        this.user = user;
        this.ip = ip;
        this.playing = b;
        this.score = i;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
