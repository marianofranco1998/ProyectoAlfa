package mx.itam.packages.proyectoalfa.classes;

import java.io.Serializable;

public class Player implements Serializable {
    private String user;
    private String password;
    private boolean playing;
    private int score;

    public Player(String user, String password) {
        this.user = user;
        this.password = password;
        this.playing = false;
        this.score = 0;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
