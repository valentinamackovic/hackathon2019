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
        return title.toLowerCase();
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
        return content.toLowerCase();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitleAndContent() {return (this.title + this.content).toLowerCase();}
}
