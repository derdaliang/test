package com.example.demo.controller;

import com.example.demo.cache.HotTagCache;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.TipsDto;
import com.example.demo.model.Question;
import com.example.demo.service.CommentService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TipsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lixuefeng
 * @date 2020/3/2 22:11
 */
@Controller
public class controller {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private TipsService tipsService;
    @Autowired
    private HotTagCache hotTagCache;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort) {
        PaginationDTO pagination = questionService.list(search, tag, sort, page, size);
        List<Question> hotTop3=questionService.list();
        List<String> tags = hotTagCache.getHots();
        Long countReplies=tipsService.countReplies();
        model.addAttribute("countReplies",countReplies);
        model.addAttribute("pagination", pagination);
        model.addAttribute("top3",hotTop3);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        return "index";
    }

}
