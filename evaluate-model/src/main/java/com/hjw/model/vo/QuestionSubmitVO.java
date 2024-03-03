package com.hjw.model.vo;

import cn.hutool.json.JSONUtil;
import com.hjw.model.dto.questionsubmit.JudgeInfo;
import com.hjw.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目提交 封装类
 *
 * @TableName question_submit
 */
@Data
public class QuestionSubmitVO implements Serializable
{
    private static final long serialVersionUID = 655665399165114261L;
    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String frontendCode;

    /**
     * 判题信息对象
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    private Integer status;

    /**
     * 题目id
     */
    private Long questionId;

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
     * 提交的用户信息
     */
    private UserVO userVO;

    /**
     * 提交的题目信息
     */
    private QuestionVO questionVO;

    /**
     * QuestionSubmit 转 QuestionSubmitVO
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit)
    {
        if (questionSubmit == null)
        {
            return null;
        }

        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);


        // 单独转换 判题信息
        String judgeInfo = questionSubmit.getJudgeInfo();
        if (judgeInfo != null)
        {
            JudgeInfo voJudgeConfig = JSONUtil.toBean(judgeInfo, JudgeInfo.class);
            questionSubmitVO.setJudgeInfo(voJudgeConfig);
        }

        return questionSubmitVO;
    }

    /**
     * QuestionSubmitVO 转 QuestionSubmit
     *
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO)
    {
        if (questionSubmitVO == null)
        {
            return null;
        }

        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);

        JudgeInfo judgeInfo = questionSubmitVO.getJudgeInfo();
        if (judgeInfo != null)
        {
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        }

        return questionSubmit;
    }

}
