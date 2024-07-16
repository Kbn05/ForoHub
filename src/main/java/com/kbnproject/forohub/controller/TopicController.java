package com.kbnproject.forohub.controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.kbnproject.forohub.dto.RequestTopic;
import com.kbnproject.forohub.dto.TopicResponse;
import com.kbnproject.forohub.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getTopics(){
        return topicService.findAll();
    }

    @PostMapping
    public ResponseEntity<TopicResponse> postTopic(@RequestBody RequestTopic requestTopic){
        return topicService.save(requestTopic);
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<TopicResponse> putTopic(@PathVariable Long id, @RequestBody RequestTopic requestTopic){
        return topicService.updateTopic(id, requestTopic);
    }

    @DeleteMapping("/{topicId}")
    public void deleteTopic(@PathVariable Long id){
        topicService.deleteById(id);
    }
}
