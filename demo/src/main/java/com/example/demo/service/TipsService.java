package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.TipsDto;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.execption.CustomizeErrorCode;
import com.example.demo.execption.CustomizeException;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.TipsMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lixuefeng
 * @date 2020/5/1 16:32
 */
@Service
public class TipsService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TipsMapper tipsMapper;
    public void insert(Tips tips) {
        if(tips.getType()== CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment=commentMapper.selectByPrimaryKey(tips.getQuestionid());
            if(dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            tipsMapper.insert(tips);
        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(tips.getQuestionid());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            tipsMapper.insert(tips);
        }

    }
    public List<TipsDto> listByStatus(int i) {
        TipsExample tipsExample=new TipsExample();
        tipsExample.createCriteria()
                .andStatusEqualTo(1);
        List<Tips> tipsList = tipsMapper.selectByExample(tipsExample);
        List<TipsDto> tipsDtos=tipsList.stream().map(tips -> {
            TipsDto tipsDto = new TipsDto();
            BeanUtils.copyProperties(tips,tipsDto);
            while(tipsDto.getType()==2){
                //去评论的父级
                if(commentMapper.selectByPrimaryKey(tipsDto.getQuestionid()).getType()==2){
                    tipsDto.setQuestionid(commentMapper.selectByPrimaryKey(tipsDto.getQuestionid()).getParentId());
                }
            }
            tipsDto.setType(1);
            tipsDto.setQuestionTitle(questionMapper.selectByPrimaryKey(tipsDto.getQuestionid()).getTitle());
            tipsDto.setUserName(userMapper.selectByPrimaryKey(tipsDto.getUserid()).getName());
            return tipsDto;
        }).collect(Collectors.toList());
        return tipsDtos;
    }

    public void updateStatus(Long id) {
        Tips tips = tipsMapper.selectByPrimaryKey(id);
        TipsExample tipsExample=new TipsExample();
        tipsExample.createCriteria()
                   .andIdEqualTo(id);
        Tips updateTips=new Tips();
        updateTips.setId(id);
        updateTips.setType(1);
        updateTips.setUserid(tips.getUserid());
        updateTips.setQuestionid(tips.getQuestionid());
        updateTips.setStatus(0);
        tipsMapper.updateByExample(updateTips,tipsExample);
    }

    public Long countReplies() {
        TipsExample tipsExample = new TipsExample();
        tipsExample.createCriteria().andStatusEqualTo(1);
        return tipsMapper.countByExample(tipsExample);
    }
}
