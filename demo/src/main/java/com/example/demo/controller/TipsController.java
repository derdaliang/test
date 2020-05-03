package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.TipsDto;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.mapper.TipsMapper;
import com.example.demo.service.CommentService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lixuefeng
 * @date 2020/4/5 16:11
 */
@Controller
public class TipsController {
    @Autowired
    private TipsService tipsService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TipsMapper tipsMapper;
    @GetMapping("/tips")
    public String tips( Model model)
    {
         List<TipsDto> list = tipsService.listByStatus(1);
        model.addAttribute("tipsList",list);
        Long countReplies=tipsService.countReplies();
        model.addAttribute("countReplies",countReplies);

        return "tips";
    }

    @GetMapping("/tips/{id}")
    public String question(
            @PathVariable(name="id") Long id,
            Model model
    ){
        Long questionid=tipsMapper.selectByPrimaryKey(id).getQuestionid();
        QuestionDto questionDto=questionService.getById(questionid);
        List<CommentDTO> comments =commentService.listByQuestionId(questionid, CommentTypeEnum.QUESTION);
        List<QuestionDto> RelatedQuestions=commentService.selectRelated(questionDto);
        //add viewCount
        questionService.incView(questionid);

        tipsService.updateStatus(id);

        model.addAttribute("question",questionDto);
        model.addAttribute("comments",comments);
        model.addAttribute("RelatedQuestions",RelatedQuestions);
        return "question";
    }
}
