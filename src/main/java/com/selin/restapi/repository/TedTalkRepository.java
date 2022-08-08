package com.selin.restapi.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.selin.restapi.model.TedTalk;
import com.selin.restapi.model.QTedTalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface TedTalkRepository extends JpaRepository<TedTalk, Long>,
        QuerydslPredicateExecutor<TedTalk>, QuerydslBinderCustomizer<QTedTalk> {
    @Override
    default void customize(
            QuerydslBindings bindings, QTedTalk root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }

}