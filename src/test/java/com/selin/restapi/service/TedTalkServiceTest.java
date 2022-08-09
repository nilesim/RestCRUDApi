package com.selin.restapi.service;

import com.querydsl.core.types.Predicate;
import com.selin.restapi.domain.model.TedTalk;
import com.selin.restapi.repository.TedTalkPredicatesBuilder;
import com.selin.restapi.repository.TedTalkRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TedTalkServiceTest {

    @Mock
    private TedTalkRepository repo;
    @InjectMocks
    private TedTalkService tedTalkService;

    private TedTalk tedTalk;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setup() {
        tedTalk = new TedTalk();

        tedTalk.setAuthor("selinimu");
        tedTalk.setTitle("Design Patterns");
        tedTalk.setDate("February 2022");
        tedTalk.setViews(22L);
        tedTalk.setLikes(1L);
        tedTalk.setLink("http://selin");
    }

    @Test
    void whenTedTalkIsCreated_thenNewCustomerDetailsAreCorrect() {

        when(repo.save(tedTalk)).thenReturn(tedTalk);

        TedTalk newTedTalk = tedTalkService.save(tedTalk);

        verify(repo, times(1)).save(tedTalk);

        assertEquals(newTedTalk.getViews().toString(), "22");
        assertEquals(newTedTalk.getLikes().toString(), "1");
        assertEquals(newTedTalk.getAuthor(), "selinimu");
        assertEquals(newTedTalk.getTitle(), "Design Patterns");
    }

    @Test
    void whenTedTalkIsDeleted_thenVerifyRepoDeleteCalled() {
        tedTalkService.delete(1L);
        verify(repo, times(1)).deleteById(1L);
    }


    @Test
    void whenTedTalkIsUpdated_thenChangedCustomerDetailsAreCorrect() {

        when(repo.save(tedTalk)).thenReturn(tedTalk);

        TedTalk newTedTalk = tedTalkService.update(tedTalk);

        verify(repo, times(1)).save(tedTalk);

        assertEquals(newTedTalk.getViews().toString(), "22");
        assertEquals(newTedTalk.getLikes().toString(), "1");
        assertEquals(newTedTalk.getAuthor(), "selinimu");
        assertEquals(newTedTalk.getTitle(), "Design Patterns");
    }

    @Test
    void testFindAll() {
        final TedTalkPredicatesBuilder builder = new TedTalkPredicatesBuilder()
                .with("title", ":", "Design Pa");
        Predicate predicate = builder.build();
        when(repo.findAll(predicate)).thenReturn(Collections.singleton(tedTalk));

        final Iterable<TedTalk> results = tedTalkService.findAll(predicate);

        assertThat(results, contains(tedTalk));
    }
}