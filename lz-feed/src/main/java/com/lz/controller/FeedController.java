package com.lz.controller;

import com.lz.entity.vo.CommentVO;
import com.lz.entity.vo.VideoVO;
import com.lz.result.CommonResult;
import com.lz.service.CommentService;
import com.lz.service.LikeService;
import com.lz.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author :Lictory
 * @date : 2024/04/06
 */

@RestController
@RequestMapping("feed")
public class FeedController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/video/getVideos")
    CommonResult<VideoVO> getVideos() {
        return videoService.getVideosForRandom();
    }

    @GetMapping("/video/getVideosFromUser")
    CommonResult<VideoVO> getVideosFromUser(String username) {
        return videoService.getVideosFromUser(username);
    }

    @PostMapping("/video/postVideo")
    CommonResult<Void> postVideo(@RequestParam("username") String username, @RequestParam("file") MultipartFile file) {
        return videoService.postVideo(file,username);
    }

    @PostMapping("/like/addLike")
    CommonResult<Void> addLike(@RequestParam("videoId") String videoId, @RequestParam("userId") String userId) {
        return likeService.addLike(videoId, userId);
    }

    @PostMapping("/like/deleteLike")
    CommonResult<Void> deleteLike(@RequestParam("videoId") String videoId, @RequestParam("userId") String userId) {
        return likeService.deleteLike(videoId, userId);
    }

    @PostMapping("/comment/addComment")
    CommonResult<Void> addComment(@RequestParam("videoId") String videoId, @RequestParam("comment") String Comment) {
        return commentService.addComment(videoId, Comment);
    }

    @PostMapping("/comment/deleteComment")
    CommonResult<Void> deleteComment(@RequestParam("videoId") String videoId) {
        return commentService.deleteComment(videoId);
    }

    @GetMapping("/comment/getComments")
    CommonResult<CommentVO> getComments(@RequestParam("videoId") String videoId) {
        return commentService.getComments(videoId);
    }

}
