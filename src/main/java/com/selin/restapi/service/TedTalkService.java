package com.selin.restapi.service;

import com.querydsl.core.types.Predicate;
import com.selin.restapi.model.TedTalk;
import com.selin.restapi.repository.TedTalkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TedTalkService {

    @Autowired
    TedTalkRepository repo;

    public TedTalk save(TedTalk tedTalk) {
        return repo.save(tedTalk);
    }

    public Iterable<TedTalk> findAll(Predicate predicate) {
        return repo.findAll(predicate);
    }

    public TedTalk update(TedTalk tedTalk) {
        return repo.save(tedTalk);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void validate(TedTalk tedTalk) {

    }

}
