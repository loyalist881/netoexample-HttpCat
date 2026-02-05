package com.example.httpPractice.catExample;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.Nulls;

public class PostCat {

    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final Integer upvotes;


    public PostCat(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }


    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    // Добавляем для вывода в консоль
    @Override
    public String toString() {
        return "PostCat{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}
