package com.duyhien.apiweb.Controllers;

import com.duyhien.apiweb.Components.SecurityUtils;
import com.duyhien.apiweb.DTO.Request.CommentDTO;
import com.duyhien.apiweb.Entities.UserEntity;
import com.duyhien.apiweb.Responses.ResponseObject;
import com.duyhien.apiweb.Responses.comment.CommentResponse;
import com.duyhien.apiweb.Services.Impl.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("${api.prefix}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final SecurityUtils securityUtils;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllComments(
            @RequestParam(value = "user_id", required = false) Long userId,
            @RequestParam("product_id") Long productId
    ) {
        List<CommentResponse> commentResponses;
        if (userId == null) {
            commentResponses = commentService.getCommentsByProduct(productId);
        } else {
            commentResponses = commentService.getCommentsByUserAndProduct(userId, productId);
        }
        return ResponseEntity.ok().body(ResponseObject.builder()
                .message("Get comments successfully")
                .status(HttpStatus.OK)
                .data(commentResponses)
                .build());
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseObject> updateComment(
            @PathVariable("id") Long commentId,
            @Valid @RequestBody CommentDTO commentDTO
    ) throws Exception {
        UserEntity loginUser = securityUtils.getLoggedInUser();
        if (!Objects.equals(loginUser.getId(), commentDTO.getUserId())) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject(
                            "You cannot update another user's comment",
                            HttpStatus.BAD_REQUEST,
                            null));

        }
        commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.ok(
                new ResponseObject(
                        "Update comment successfully",
                        HttpStatus.OK, null));
    }
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseObject> insertComment(
            @Valid @RequestBody CommentDTO commentDTO
    ) {
        UserEntity loginUser = securityUtils.getLoggedInUser();
        if(loginUser.getId() != commentDTO.getUserId()) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject(
                            "You cannot comment as another user",
                            HttpStatus.BAD_REQUEST,
                            null));
        }
        commentService.insertComment(commentDTO);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Insert comment successfully")
                        .status(HttpStatus.OK)
                        .build());
    }
}
