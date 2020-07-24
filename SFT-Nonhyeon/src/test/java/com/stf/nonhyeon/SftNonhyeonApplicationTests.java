package com.stf.nonhyeon;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.sft.nonhyeon.dto.Notice;
import com.sft.nonhyeon.persistence.NoticeRepositoty;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class SftNonhyeonApplicationTests {

	@Autowired
	private NoticeRepositoty noticeRp;

	@Test
	public void contextLoads() {
		log.info("===공지사항적재===");
		noticeRp.deleteAll();
		Notice notice1 = new Notice();
		notice1.setTitle("★★★SFT 논현지사 신규 런칭  기념 이벤트★★★");
		notice1.setContent("신규");
		notice1.setWriter("SFT 논현지사");
		noticeRp.save(notice1);
		Notice notice2 = new Notice();
		notice2.setTitle("★★★SFT 논현지사 공지1★★★");
		notice2.setContent("공지2");
		notice2.setWriter("SFT 논현지사");
		noticeRp.save(notice2);

		for (Notice notice : noticeRp.findAll()) {
			log.info(notice.toString());
		}

		// 초기페이징
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<Notice> result=noticeRp.findAll(noticeRp.makePredicate(null, null),pageable);
		log.info("===============결과==============");
		for(Notice notice:result) {
			log.info("getContent :"+notice+" ");
		}
		log.info("끝");
	}


	@Test
	public void contextLoads2() {

		log.info("===============결과==============");
		Long bno=(long) 1021;
		noticeRp.findById(bno).ifPresent(board -> log.info("vo :"+board));
		log.info("===============결과 끝ㄴ==============");
		log.info("끝");
	}
	@Test
	public void contextLoads3() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<Notice> result=noticeRp.findAll(noticeRp.makePredicate(null, null),pageable);
		log.info("===============결과==============");
		for(Notice notice:result) {
			log.info("getContent :"+notice+" ");
		}
		log.info("===============결과 끝==============");
		log.info("끝");
	}
	
	@Test
	public void contextLoads4() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<Notice> result=noticeRp.findAll(noticeRp.makePredicate(null, null),pageable);
		log.info("===============결과==============");
		for(Notice notice:result) {
			log.info("getContent :"+notice+" ");
		}
		log.info("===============결과 끝==============");
		log.info("끝");
	}
	
}
