package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.QuestionDto;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.service.CommentService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author lixuefeng
 * @date 2020/3/19 21:43
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TipsService tipsService;
    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name="id") Long id,
            Model model
    ){
        QuestionDto questionDto=questionService.getById(id);
        List<CommentDTO> comments =commentService.listByQuestionId(id, CommentTypeEnum.QUESTION);
        List<QuestionDto> RelatedQuestions=commentService.selectRelated(questionDto);
        //add viewCount
        questionService.incView(id);
//        commentService.updateFlagByQuestion(id);
        Long countReplies=tipsService.countReplies();
        model.addAttribute("countReplies",countReplies);
        model.addAttribute("question",questionDto);
        model.addAttribute("comments",comments);
        model.addAttribute("RelatedQuestions",RelatedQuestions);
        return "question";
    }
}
