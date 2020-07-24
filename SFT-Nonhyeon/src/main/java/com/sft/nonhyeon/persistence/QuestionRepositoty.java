package com.sft.nonhyeon.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.sft.nonhyeon.dto.Question;
import com.stf.nonhyeon.dto.QQuestion;

public interface QuestionRepositoty extends CrudRepository<Question, Long>,QuerydslPredicateExecutor<Question>  {
	public default Predicate makePredicate(String type, String keyword) {

		BooleanBuilder builder = new BooleanBuilder();

		 QQuestion question = QQuestion.question;

		 builder.and(question.bno.gt(0));
		return builder;
	}
}
