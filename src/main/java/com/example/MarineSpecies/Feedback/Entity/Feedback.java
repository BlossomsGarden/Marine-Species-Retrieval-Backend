package com.example.MarineSpecies.Feedback.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.MarineSpecies.Common.BaseEntity;
import lombok.Data;

/**
 * feedback表实体类
 *
 * @author ************
 * @since 2024-04-23
 */
@Data
@TableName(value = "feedback")
public class Feedback extends BaseEntity {
    @TableId(type = IdType.AUTO)
    Integer id;
    String title;
    String content;
    Integer uploadId;
    //没时间实现回复了，弃置
    String reply;
    //没时间实现回复了，弃置
    Integer reviewId;
}
