package model;

import java.util.Date;

/**
 * Created by HUZY_KAMZ on 11/8/2016.
 */

public class News {

    public String Headlines;
    public String Details;
    public String NewsPhoto;
    public String newsdate;

    public String getNewsdate() {
        return newsdate;
    }

    public void setNewsdate(String newsdate) {
        this.newsdate = newsdate;
    }

    public String getNewsPhoto() {
        return NewsPhoto;
    }

    public void setNewsPhoto(String newsPhoto) {
        NewsPhoto = newsPhoto;
    }

    public String getHeadlines() {
        return Headlines;
    }

    public void setHeadlines(String headlines) {
        Headlines = headlines;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
