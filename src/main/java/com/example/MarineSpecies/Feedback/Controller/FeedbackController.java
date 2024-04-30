package com.example.MarineSpecies.Feedback.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.MarineSpecies.Common.MyResponse;
import com.example.MarineSpecies.Feedback.Entity.DTO.FeedbackDTO;
import com.example.MarineSpecies.Feedback.Entity.Feedback;
import com.example.MarineSpecies.Feedback.Mapper.FeedbackMapper;
import com.example.MarineSpecies.LoginManager.Entity.Admin;
import com.example.MarineSpecies.LoginManager.Entity.User;
import com.example.MarineSpecies.LoginManager.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * (BackstageUserPermission)表实体类
 *
 * @author ************
 * @since 2023-10-17
 */
@RestController
@RequestMapping("feedback")
@Slf4j
public class FeedbackController {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    UserMapper userMapper;

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
        feedback.setUploadId(user.getId());
        return MyResponse.ok(feedbackMapper.insert(feedback));
    }

    /**
     * 用户获取自己的反馈信息
     */
    @GetMapping("my")
    public MyResponse<List<Feedback>> my(@SessionAttribute("user")User user){
        LambdaQueryWrapper<Feedback> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Feedback::getUploadId,user.getId());
        return MyResponse.ok(feedbackMapper.selectList(wrapper));
    }

    /**
     * 管理员获得全部反馈信息
     * 自己的可以直接从缓存取信息，管理员这个得注入DTO
     */
    @GetMapping("all")
    public MyResponse<List<FeedbackDTO>> all(@SessionAttribute("admin")Admin admin){
        List<Feedback> feedbackList=feedbackMapper.selectList(null);
        List<FeedbackDTO> feedbackDTOList=new ArrayList<>();
        for(Feedback f :feedbackList){
            FeedbackDTO feedbackDTO=new FeedbackDTO();
            BeanUtils.copyProperties(f,feedbackDTO);
            LambdaQueryWrapper<User> wrapper =new LambdaQueryWrapper<>();
            wrapper.eq(User::getId,f.getUploadId());
            User uploaderInfo=userMapper.selectOne(wrapper);
            feedbackDTO.setUploaderAvatarUrl(uploaderInfo.getAvatarUrl());
            feedbackDTO.setUploaderName(uploaderInfo.getName());
            feedbackDTOList.add(feedbackDTO);
        }
        return MyResponse.ok(feedbackDTOList);
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
