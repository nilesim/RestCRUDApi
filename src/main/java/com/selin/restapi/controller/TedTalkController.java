package com.selin.restapi.controller;

import com.google.common.base.Preconditions;
import com.selin.restapi.model.TedTalk;
import com.selin.restapi.service.TedTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ted-talks")
@Slf4j
public class TedTalkController {

    @Autowired
    TedTalkService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TedTalk> create(@RequestBody TedTalk tedTalk) {
        TedTalk talkSaved;
        service.validate(tedTalk);
        talkSaved = service.save(tedTalk);
        return new ResponseEntity<TedTalk>(talkSaved, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    Iterable<TedTalk> getAll(
            @PageableDefault Pageable pageable,
            @QuerydslPredicate(root = TedTalk.class) Predicate predicate ) {
        return service.findAll(predicate);
    }

    @GetMapping("/from-cache")
    @ResponseBody
    List<TedTalk> getAll() {
        return service.getAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TedTalk> update(@RequestBody TedTalk tedTalk) {
        Preconditions.checkNotNull(tedTalk);
        TedTalk updatedTedTalk = service.update(tedTalk);
        if (updatedTedTalk == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedTedTalk);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

