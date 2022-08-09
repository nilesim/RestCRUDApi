package com.selin.restapi.service;

import com.querydsl.core.types.Predicate;
import com.selin.restapi.model.TedTalk;
import com.selin.restapi.repository.TedTalkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Slf4j
public class TedTalkService {

    @Autowired
    TedTalkRepository repo;

    public TedTalk save(TedTalk tedTalk) {
        return repo.save(tedTalk);
    }

    @Cacheable(value = "tedTalkCache")
    public List<TedTalk> getAll() {
        return repo.findAll();
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
