package com.kbnproject.forohub.service;

import com.kbnproject.forohub.dto.RequestTopic;
import com.kbnproject.forohub.dto.TopicResponse;
import com.kbnproject.forohub.model.Topic;
import com.kbnproject.forohub.repository.CourseRepository;
import com.kbnproject.forohub.repository.TopicRepository;
import com.kbnproject.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    public void deleteById(Long id) {
        topicRepository.deleteById(id);
    }

    public ResponseEntity<List<TopicResponse>> findAll() {
        List<Topic> topics = topicRepository.findAll();
        List<TopicResponse> response = topics.stream()
                .map(topic -> {
                    TopicResponse topicResponse = new TopicResponse();
                    topicResponse.setId(topic.getId());
                    topicResponse.setTitle(topic.getTitle());
                    topicResponse.setMessage(topic.getMessage());
                    topicResponse.setCreationDate(topic.getCreationDate());
                    return topicResponse;
                })
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<TopicResponse> save(RequestTopic requestTopic) {
        Topic topic = new Topic();
        topic.setTitle(requestTopic.getTitle());
        topic.setAuthor(userRepository.getReferenceById(requestTopic.getAuthorId()));
        topic.setCreationDate(LocalDate.now());
        topic.setCourseName(courseRepository.getReferenceById(requestTopic.getCourseId()));
        topic.setMessage(requestTopic.getMessage());
        topic.setStatus(String.valueOf(HttpStatus.OK.value()));

        Topic savedTopic = topicRepository.save(topic);

        TopicResponse response = new TopicResponse();
        response.setId(savedTopic.getId());
        response.setCreationDate(savedTopic.getCreationDate());
        response.setMessage(savedTopic.getMessage());
        response.setTitle(savedTopic.getTitle());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<TopicResponse> updateTopic(Long id, RequestTopic requestTopic) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Topic topic = optionalTopic.get();
        topic.setTitle(requestTopic.getTitle());
        topic.setStatus(String.valueOf(HttpStatus.OK.value()));
        topic.setMessage(requestTopic.getMessage());
        topic.setCourseName(courseRepository.getReferenceById(requestTopic.getCourseId()));
        topic.setAuthor(userRepository.getReferenceById(requestTopic.getAuthorId()));
        topic.setCreationDate(LocalDate.now());

        Topic updatedTopic = topicRepository.save(topic);

        TopicResponse response = new TopicResponse();
        response.setId(updatedTopic.getId());
        response.setTitle(updatedTopic.getTitle());
        response.setMessage(updatedTopic.getMessage());
        response.setCreationDate(updatedTopic.getCreationDate());

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
