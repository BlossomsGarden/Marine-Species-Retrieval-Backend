package com.example.MarineSpecies.Feedback.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.MarineSpecies.Common.BaseEntity;
import lombok.Data;

/**
 * feedback表实体类
 *
 * @author 2152189 汪林辉
 * @since 2024-04-23
 */
@Data
@TableName(value = "feedback")
public class Feedback extends BaseEntity {
    @TableId(type = IdType.AUTO)
    Integer id;
    String title;
    String content;
    Integer upload_id;
    String reply;
    Integer review_id;
}
