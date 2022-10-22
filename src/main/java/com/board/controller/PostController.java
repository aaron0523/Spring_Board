package com.board.controller;

import com.board.Service.PostService;
import com.board.Service.PagingService;
import com.board.Service.UserService;
import com.board.dto.PostRequest;
import com.board.paging.Pagination;

import com.board.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class PostController {

    final private PostService postService;
    final private PagingService pagingService;
    final private UserService userService;
    final private HttpSession httpSession;

    /* 게시글 목록 */
    @GetMapping("/")
    public String list(Model model, @RequestParam(defaultValue = "1") int page) throws SQLException, ClassNotFoundException {
        Pagination pageInfo = pagingService.paging(page);
        List<Post> postList = postService.getPostList(pageInfo.getStartIndex(), pageInfo.getPageSize());
        if(httpSession.getAttribute("loginUser") == null){
            userService.login("root", "root");
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("postList", postList);
        model.addAttribute("currentUser", httpSession.getAttribute("loginUser"));
        return "board/index";
    }
    @PostMapping("/save")
    private String save(@ModelAttribute("postRequest") PostRequest postRequest){
        postService.save(postRequest);
        return "redirect:/board/";
    }
    @GetMapping("/savePage")
    public String moveSavePage(Model model){
        model.addAttribute("currentUser", httpSession.getAttribute("loginUser"));
        return "board/addPost";
    }

    /* 게시글 상세 */
    @GetMapping("/detail/{postId}")
    private String getPostInfo(@PathVariable("postId") int postId, Model model) {
        Post postInfo = postService.getPostDetail(postId);
        model.addAttribute("postInfo", postInfo);
        return "board/detail";
    }
    /* 게시글 수정 */
    @GetMapping("/{postId}/editPage")
    private String moveEditPage(@PathVariable("postId") int postId,@ModelAttribute("postRequest") PostRequest postRequest, Model model) {
        Post postInfo = postService.getPostDetail(postId);
        model.addAttribute("postInfo", postInfo);
        return "board/update";
    }

    @PostMapping("/{postId}/update")
    private String updatePost(@PathVariable("postId") int postId, @ModelAttribute("postRequest") PostRequest postRequest, Model model) {
        postService.update(postRequest, postId);
        return "redirect:/board/detail/" + postId;
    }

    @RequestMapping("/{postId}/delete")
    private String deletePostById(@PathVariable("postId") int postId, Model model) {
        postService.deleteById(postId);
        return "redirect:/board/";
    }
}
