package com.example.MarineSpecies.Feedback.Entity.DTO;

import com.example.MarineSpecies.Feedback.Entity.Feedback;
import lombok.Data;

/**
 * 因为不能让用户直接请求getInfo，被拦截的话太危险了
 * 于是将反馈展示时需要的用户头像、姓名等信息塞在DTO里面
 */
@Data
public class FeedbackDTO extends Feedback {
    String uploaderName;
    String uploaderAvatarUrl;
}
