package com.selin.restapi.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyIterable.emptyIterable;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.IsNot.not;

import com.selin.restapi.domain.model.TedTalk;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class JPAQuerydslIntegrationTest {

    @Autowired
    TedTalkRepository repo;

    private TedTalk talk1;

    private TedTalk talk2;

    @Before
    public void init() {
        talk1 = new TedTalk();

        talk1.setAuthor("selinimu");
        talk1.setTitle("Design Patterns");
        talk1.setDate("February 2022");
        talk1.setViews(22L);
        talk1.setLikes(1L);
        talk1.setLink("http://selin");
        talk1 = repo.save(talk1);

        talk2 = new TedTalk();
        talk2.setAuthor("selinimu");
        talk2.setTitle("Design Princess");
        talk2.setDate("February 2022");
        talk2.setViews(222L);
        talk2.setLikes(11L);
        talk2.setLink("http://selin");
        talk2 = repo.save(talk2);
    }

    @Test
    public void givenAuthor_whenGettingListOfTedTalks_thenCorrect() {
        final TedTalkPredicatesBuilder builder = new TedTalkPredicatesBuilder()
                .with("author", ":", "selinimu");
        final Iterable<TedTalk> results = repo.findAll(builder.build());
        assertThat(results, containsInAnyOrder(talk1, talk2));
    }


    @Test
    public void givenAuthorAndTitle_whenGettingListOfTedTalks_thenCorrect() {
        final TedTalkPredicatesBuilder builder = new TedTalkPredicatesBuilder()
                .with("author", ":", "selinimu")
                .with("title", ":", "Design Patterns");

        final Iterable<TedTalk> results = repo.findAll(builder.build());

        assertThat(results, contains(talk1));
        assertThat(results, not(contains(talk2)));
    }

    @Test
    public void givenAuthorAndLikes_whenGettingListOfTedTalks_thenCorrect() {
        final TedTalkPredicatesBuilder builder = new TedTalkPredicatesBuilder()
                .with("author", ":", "selinimu")
                .with("likes", ">", "10");

        final Iterable<TedTalk> results = repo.findAll(builder.build());
        assertThat(results, contains(talk2));
    }

    @Test
    public void givenViewsAndLikes_whenGettingListOfTedTalks_thenCorrect() {
        final TedTalkPredicatesBuilder builder = new TedTalkPredicatesBuilder()
                .with("views", "<", "-500000")
                .with("likes", ">", "10");

        final Iterable<TedTalk> results = repo.findAll(builder.build());
        assertThat(results, emptyIterable());
    }

    @Test
    public void givenPartialTitle_whenGettingListOfTedTalks_thenCorrect() {
        final TedTalkPredicatesBuilder builder = new TedTalkPredicatesBuilder()
                .with("title", ":", "Design Pa");

        final Iterable<TedTalk> results = repo.findAll(builder.build());

        assertThat(results, contains(talk1));
        assertThat(results, not(contains(talk2)));
    }


}