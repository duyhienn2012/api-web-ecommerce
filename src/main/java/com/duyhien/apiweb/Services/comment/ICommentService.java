package com.duyhien.apiweb.Services.comment;

import com.duyhien.apiweb.DTO.CommentDTO;
import com.duyhien.apiweb.Entities.CommentEntity;
import com.duyhien.apiweb.Exceptions.DataNotFoundException;
import com.duyhien.apiweb.Responses.comment.CommentResponse;


import java.util.List;

public interface ICommentService {
    CommentEntity insertComment(CommentDTO comment);

    void deleteComment(Long commentId);
    void updateComment(Long id, CommentDTO commentDTO) throws DataNotFoundException;

    List<CommentResponse> getCommentsByUserAndProduct(Long userId, Long productId);
    List<CommentResponse> getCommentsByProduct(Long productId);

}
