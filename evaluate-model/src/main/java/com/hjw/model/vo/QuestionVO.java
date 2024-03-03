package com.hjw.model.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.hjw.model.dto.question.JudgeConfig;
import com.hjw.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目 返回前端的实体类
 *
 * @TableName question
 */
@Data
public class QuestionVO implements Serializable
{
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 题目标签分类 集合
     */
    private List<String> tags;


    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;


    /**
     * 判题配置（json 数组）
     */
    private JudgeConfig judgeConfig;

    /**
     * 题目难度
     */
    private Integer difficulty;


    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人信息
     */
    private UserVO userVO;

    /**
     * 判断当前用户是否已经做过该题
     */
    private boolean hasTried = false;

    /**
     * 用户代码
     */
    private String submitCode;

    private static final long serialVersionUID = 1L;


    /**
     * Question 转 QuestionVO
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question)
    {
        if (question == null)
        {
            return null;
        }

        QuestionVO questionVO = new QuestionVO();
        // BeanUtil.copyProperties(question, questionVO,CopyOptions.create().setIgnoreError(true));
        BeanUtils.copyProperties(question, questionVO);

        // 单独转换 标签集合
        String tagStr = question.getTags();
        List<String> tagList = JSONUtil.toList(tagStr, String.class);
        questionVO.setTags(tagList);

        // 单独转换 判题设置
        String config = question.getJudgeConfig();
        JudgeConfig voJudgeConfig = JSONUtil.toBean(config, JudgeConfig.class);
        questionVO.setJudgeConfig(voJudgeConfig);


        return questionVO;
    }

    /**
     * QuestionVO 转 Question
     *
     * @return
     */
    public static Question voToObj(QuestionVO questionVO)
    {
        if (questionVO == null)
        {
            return null;
        }

        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);

        Gson gson = new Gson();
        // 单独转换 标签集合
        List<String> tagsList = questionVO.getTags();
        if (CollUtil.isNotEmpty(tagsList))
        {
            String tagJson = gson.toJson(tagsList);
            question.setTags(tagJson);
        }

        // 单独转换 判题设置
        JudgeConfig config = questionVO.getJudgeConfig();
        if (config != null)
        {
            question.setJudgeConfig(JSONUtil.toJsonStr(config));
        }

        return question;
    }
}
