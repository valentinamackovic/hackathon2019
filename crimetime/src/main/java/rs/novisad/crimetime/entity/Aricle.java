package rs.novisad.crimetime.entity;

public class Aricle {
    private String title;
    private String url;
    private String content;

    public Aricle(String title, String url, String content) {
        this.title = title;
        this.url = url;
        this.content = content;
    }

    public Aricle() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
