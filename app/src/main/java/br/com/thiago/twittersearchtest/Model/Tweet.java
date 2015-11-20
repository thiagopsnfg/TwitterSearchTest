package br.com.thiago.twittersearchtest.Model;

/**
 * Created by Thiago on 17/11/2015.
 */
public class Tweet {
    private final int id;
    private final String user;
    private final String text;
    private final String image;
    private final String link;

    public Tweet(int Id, String user, String text, String image, String link) {
        this.id = Id;
        this.user = user;
        this.text = text;
        this.image = image;
        this.link = link;

    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }
}
