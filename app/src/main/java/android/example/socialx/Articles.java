package android.example.socialx;

import java.io.Serializable;

public class Articles implements Serializable {

    Source source = null;
    String title = "";
    String description = "";
    String url = "";
    String urlToImg = "";
    String publishedAt = "";

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImg() {
        return urlToImg;
    }

    public void setUrlToImg(String urlToImg) {
        this.urlToImg = urlToImg;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
