package com.simanjit.moviesearch;

public class BookmarkList {
    public BookmarkList(int id, String thumbnail, String title, String released, String runtime, String genre, String director, String actors, String language, String boxOffice, String production) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.title = title;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.language = language;
        this.boxOffice = boxOffice;
        this.production = production;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String thumbnail;
    private String title;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    private String language;
    private String boxOffice;

//    public BookmarkList(String thumbnail, String title, String released, String runtime, String genre, String director, String actors, String language, String boxOffice, String production) {
//        this.thumbnail = thumbnail;
//        this.title = title;
//        this.released = released;
//        this.runtime = runtime;
//        this.genre = genre;
//        this.director = director;
//        this.actors = actors;
//        this.language = language;
//        this.boxOffice = boxOffice;
//        this.production = production;
//    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    private String production;

}
