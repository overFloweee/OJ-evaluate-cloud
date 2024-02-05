package com.hjw.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjw.model.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86157
* @description 针对表【question(题目)】的数据库操作Mapper
* @createDate 2024-01-22 21:50:08
* @Entity generator.domain.Question
*/
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}




