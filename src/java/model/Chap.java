package model;

public class Chap {
    private int mangaId;
    private int chapId;
    private String chapName;
    private String link;

    public Chap() {
    }

    public Chap(int mangaId, int chapId, String chapName, String link) {
        this.mangaId = mangaId;
        this.chapId = chapId;
        this.chapName = chapName;
        this.link = link;
    }

    public int getMangaId() {
        return mangaId;
    }

    public void setMangaId(int mangaId) {
        this.mangaId = mangaId;
    }

    public int getChapId() {
        return chapId;
    }

    public void setChapId(int chapId) {
        this.chapId = chapId;
    }

    public String getChapName() {
        return chapName;
    }

    public void setChapName(String chapName) {
        this.chapName = chapName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
}
