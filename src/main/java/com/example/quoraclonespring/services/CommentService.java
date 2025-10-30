package com.example.quoraclonespring.services;

import com.example.quoraclonespring.dtos.CommentDTO;
import com.example.quoraclonespring.models.Answer;
import com.example.quoraclonespring.models.Comment;
import com.example.quoraclonespring.repositories.AnswerRepository;
import com.example.quoraclonespring.repositories.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private AnswerRepository answerRepository;

    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    public List<Comment> getCommentsByAnswerId(Long answerId, int page, int size) {
        return commentRepository.findByAnswerId(answerId, PageRequest.of(page, size)).getContent();
    }
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getRepliesByCommentId(Long CommentId, int page, int size) {
        return commentRepository.findByParentCommentId(CommentId, PageRequest.of(page, size)).getContent();
    }

    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());

        Optional<Answer> answer = answerRepository.findById(commentDTO.getAnswerId());
        answer.ifPresent(comment::setAnswer);

        if (commentDTO.getParentCommentId() != null) {
            Optional<Comment> parentComment = commentRepository.findById(commentDTO.getParentCommentId());
            parentComment.ifPresent(comment::setParentComment);
        }

        return commentRepository.save(comment);
    }
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
