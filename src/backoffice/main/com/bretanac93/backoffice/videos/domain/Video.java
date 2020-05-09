package com.bretanac93.backoffice.videos.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Video {
    private final String id;
    private final String title;
    private final String description;
    private final Long duration;

    public Video(String id, String title, String description, Long duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public Video() {
        this(null, null, null, null);
    }

    public static Video fromPrimitives(Map<String, Object> plainData) {
        return new Video(
            (String) plainData.get("id"),
            (String) plainData.get("title"),
            (String) plainData.get("description"),
            (Long) plainData.get("duration")
        );
    }

    public String id() {
        return this.id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public Long duration() {
        return duration;
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("id", id);
            put("title", title);
            put("description", description);
            put("duration", duration);
        }};
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, duration);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Video that = (Video) obj;
        return id.equals(that.id) &&
            title.equals(that.title) &&
            description.equals(that.description) &&
            duration.equals(that.duration);
    }
}
