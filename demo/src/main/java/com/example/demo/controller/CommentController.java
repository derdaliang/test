package com.example.demo.controller;

import com.example.demo.dto.CommentCreateDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.ResultDTO;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.execption.CustomizeErrorCode;
import com.example.demo.model.Comment;
import com.example.demo.model.Tips;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.TipsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lixuefeng
 * @date 2020/4/5 16:11
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private TipsService tipsService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                                    HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if(commentCreateDTO==null|| StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_ENPTY);
        }
        Comment comment = new Comment();
        Tips tips = new Tips();

        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setFlag(0);

        tips.setUserid(user.getId());
        tips.setQuestionid(commentCreateDTO.getParentId());
        tips.setStatus(1);//1 is unread,0 is read
        tips.setType(commentCreateDTO.getType());

        tipsService.insert(tips);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public  ResultDTO<List<CommentDTO>> comments(@PathVariable(name="id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByQuestionId(id, CommentTypeEnum.COMMENT);

        return ResultDTO.okOf(commentDTOS);
    }
}
