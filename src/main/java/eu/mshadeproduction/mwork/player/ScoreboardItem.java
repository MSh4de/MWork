package eu.mshadeproduction.mwork.player;

import java.util.List;

public class ScoreboardItem {

    private String title;
    private List<String> lines;

    public ScoreboardItem(String title, List<String> lines) {
        this.title = title;
        this.lines = lines;
    }

    private ScoreboardItem() {
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLines() {
        return lines;
    }
}
