package com.test.airbuzz.models;

public class NewsModelClass {
    String means;
    String headLines;
    String mainNews;

    public NewsModelClass(){

    }

    public NewsModelClass(String means, String headLines,String mainNews){
        this.means = means;
        this.headLines = headLines;
        this.mainNews =mainNews;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getHeadLines() {
        return headLines;
    }

    public void setHeadLines(String headLines) {
        this.headLines = headLines;
    }

    public String getMainNews() {
        return mainNews;
    }

    public void setMainNews(String mainNews) {
        this.mainNews = mainNews;
    }
}
