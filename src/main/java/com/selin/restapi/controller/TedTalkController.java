package com.selin.restapi.controller;

import com.google.common.base.Preconditions;
import com.selin.restapi.domain.dto.TedTalkDTO;
import com.selin.restapi.domain.model.TedTalk;
import com.selin.restapi.service.TedTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/ted-talks")
@Slf4j
public class TedTalkController {

    @Autowired
    TedTalkService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TedTalkDTO> create(@RequestBody TedTalkDTO tedTalkDTO) {
        TedTalk tedTalk = convertToEntity(tedTalkDTO);
        service.validate(tedTalk);
        TedTalk talkSaved = service.save(tedTalk);
        TedTalkDTO result = convertToDto(talkSaved);
        return new ResponseEntity<TedTalkDTO>(result, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    Iterable<TedTalkDTO> getAll(
            @PageableDefault Pageable pageable,
            @QuerydslPredicate(root = TedTalk.class) Predicate predicate ) {
        Iterable<TedTalk> tedTalkList = service.findAll(predicate);
        return StreamSupport.stream(tedTalkList.spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TedTalkDTO> update(@RequestBody TedTalkDTO tedTalkDTO) {
        Preconditions.checkNotNull(tedTalkDTO);
        TedTalk updatedTedTalk = service.update(convertToEntity(tedTalkDTO));
        if (updatedTedTalk == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(convertToDto(updatedTedTalk));
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private TedTalk convertToEntity(TedTalkDTO tedTalkDTO) {
        TedTalk tedTalk = new TedTalk();
        BeanUtils.copyProperties(tedTalkDTO, tedTalk);
        return tedTalk;
    }

    private TedTalkDTO convertToDto(TedTalk tedTalk) {
        TedTalkDTO tedTalkDTO = new TedTalkDTO();
        BeanUtils.copyProperties(tedTalk, tedTalkDTO);
        return tedTalkDTO;
    }

}

