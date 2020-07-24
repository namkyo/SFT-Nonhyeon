package com.sft.nonhyeon.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.sft.nonhyeon.dto.Consulting;
import com.stf.nonhyeon.dto.QConsulting;

public interface ConsultingRepositoty extends CrudRepository<Consulting, Long>, QuerydslPredicateExecutor<Consulting> {
	public default Predicate makePredicate(String type, String keyword) {

		BooleanBuilder builder = new BooleanBuilder();

		QConsulting notice = QConsulting.consulting;

		builder.and(notice.bno.gt(0));
		return builder;
	}
	
}
