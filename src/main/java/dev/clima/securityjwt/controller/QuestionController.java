package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.ResponsesInfoDTO;
import dev.clima.securityjwt.entity.Question;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.QuestionRepository;
import dev.clima.securityjwt.repository.UserDAO;
import dev.clima.securityjwt.repository.UserResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final UserResponseRepository userResponseRepository;
    private final UserDAO userDAO;

    @GetMapping
    public ResponseEntity<List<Question>> getCompanies() {
        var response = questionRepository.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getOne(@PathVariable Long id) {
        var response = questionRepository.findById(id)
                .orElse(null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-info/{id}")
    public ResponseEntity<ResponsesInfoDTO> getInfo(@PathVariable Long id) {
        var email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userDAO.findByEmail(email).map(User::getId).orElse(0L);
        var answered = userResponseRepository.existsByQuestionIdAndUserId(id, user);
        var responses = userResponseRepository.countValues(id);
        var response = new ResponsesInfoDTO(answered, responses);
        return ResponseEntity.ok(response);
    }

//    public ResponseEntity<Void> answdser(Long id) {
//
//    }

}
