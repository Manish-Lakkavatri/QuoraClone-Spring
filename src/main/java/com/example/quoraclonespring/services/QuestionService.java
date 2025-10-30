package com.example.quoraclonespring.services;

import com.example.quoraclonespring.dtos.QuestionDTO;
import com.example.quoraclonespring.models.Question;
import com.example.quoraclonespring.models.Tag;
import com.example.quoraclonespring.models.User;
import com.example.quoraclonespring.repositories.QuestionRepository;
import com.example.quoraclonespring.repositories.TagRepository;
import com.example.quoraclonespring.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<Question> getQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question createQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());

        Optional<User> user = userRepository.findById(questionDTO.getUserId());
        user.ifPresent(question::setUser);

        Set<Tag> tags = questionDTO.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        question.setTags(tags);

        return questionRepository.save(question);
    }
    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

}
