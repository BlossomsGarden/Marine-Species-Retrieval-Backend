package com.example.MarineSpecies.Feedback.Controller;

import com.example.MarineSpecies.Common.MyResponse;
import com.example.MarineSpecies.Feedback.Entity.Feedback;
import com.example.MarineSpecies.Feedback.Mapper.FeedbackMapper;
import com.example.MarineSpecies.LoginManager.Entity.Admin;
import com.example.MarineSpecies.LoginManager.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (BackstageUserPermission)表实体类
 *
 * @author 2152189 汪林辉
 * @since 2023-10-17
 */
@RestController
@RequestMapping("feedback")
@Slf4j
public class FeedbackController {
    @Autowired
    FeedbackMapper feedbackMapper;

    /**
     * 用户新增反馈
     * @param user session注入属性
     * @param feedback 传入参数
     */
    @PostMapping("new")
    public MyResponse<Integer> newFeedback(
            @SessionAttribute("user") User user,
            @RequestBody Feedback feedback
    ){
        feedback.setUpload_id(user.getId());
        return MyResponse.ok(feedbackMapper.insert(feedback));
    }


    /**
     * 管理员回复反馈
     * @param admin session自动注入的管理员参数
     * @param feedback 传入参数
     */
    @PostMapping("reply")
    public MyResponse<Integer> replyFeedback(
            @SessionAttribute("admin") Admin admin,
            @RequestBody Feedback feedback
    ){
        return MyResponse.ok(feedbackMapper.updateById(feedback));
    }
}
