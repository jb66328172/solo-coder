package com.example.mybatis.mapper;

import com.example.mybatis.entity.Score;

import java.util.List;
import java.util.Map;

public interface ScoreMapper {

    List<Score> selectByCourseId(Long courseId);

    Map<String, Object> selectStatisticsByCourseId(Long courseId);

    int insertBatch(List<Score> scores);
}
