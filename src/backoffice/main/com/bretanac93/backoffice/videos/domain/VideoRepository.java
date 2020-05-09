package com.bretanac93.backoffice.videos.domain;

import com.bretanac93.shared.domain.criteria.Criteria;

import java.util.List;

public interface VideoRepository {
    void save(Video video);
    List<Video> findAll();
    List<Video> find(Criteria criteria);
}
