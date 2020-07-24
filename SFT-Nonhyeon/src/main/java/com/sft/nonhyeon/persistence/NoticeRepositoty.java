package com.sft.nonhyeon.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.sft.nonhyeon.dto.Notice;
import com.stf.nonhyeon.dto.QNotice;

public interface NoticeRepositoty extends CrudRepository<Notice, Long>, QuerydslPredicateExecutor<Notice> {
	public default Predicate makePredicate(String type, String keyword) {

		BooleanBuilder builder = new BooleanBuilder();

		QNotice notice = QNotice.notice;

		builder.and(notice.bno.gt(0));
		return builder;
	}

	// @Query("SELECT b.bno, b.title, b.writer, b.regdate, count(r) FROM WebBoard b
	// " +
	// " LEFT OUTER JOIN b.replies r WHERE b.title like %?1% AND b.bno > 0 GROUP BY
	// b")
	// public List<Notice> getListNoticeTwo();
}
