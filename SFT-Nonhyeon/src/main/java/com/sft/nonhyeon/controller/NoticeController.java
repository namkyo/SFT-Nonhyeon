package com.sft.nonhyeon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sft.nonhyeon.dto.Notice;
import com.sft.nonhyeon.persistence.NoticeRepositoty;
import com.sft.nonhyeon.util.Common;
import com.sft.nonhyeon.vo.PageMaker;
import com.sft.nonhyeon.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@Log
public class NoticeController {
	@Autowired
	private NoticeRepositoty repo;

	// 게시판리스트 이동
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/notice")
	public ModelAndView view(String bno, @ModelAttribute("pageVO") PageVO vo, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<Notice> result = repo.findAll(repo.makePredicate(null, null), pageable);
		mv.addObject("notice", new PageMaker(result));
		log.info("BNO: " + bno);
		Common.toPrintIp(req);
		mv.setViewName("sft/notice");
		return mv;
	}

	// 게시판글쓰기화면이동
	@GetMapping("/noticeWrite")
	public ModelAndView writerView(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		Common.toPrintIp(req);
		mv.setViewName("sft/noticeWrite");
		return mv;
	}

	// 게시글 읽기화면 이동
	@GetMapping("/noticeView")
	public ModelAndView noticeView(HttpServletRequest req, Long bno, @ModelAttribute("pageVO") PageVO vo) {
		ModelAndView mv = new ModelAndView();
		Common.toPrintIp(req);
		log.info("BNO: " + bno);
		repo.findById(bno).ifPresent(board -> mv.addObject("vo", board));
		mv.setViewName("sft/noticeView");
		return mv;
	}

	// 게시글수정화면 이동
	@GetMapping("/noticeModify")
	public ModelAndView noticeModifyView(HttpServletRequest req, Long bno, @ModelAttribute("pageVO") PageVO vo) {
		ModelAndView mv = new ModelAndView();
		Common.toPrintIp(req);
		log.info("BNO: " + bno);
		repo.findById(bno).ifPresent(board -> mv.addObject("vo", board));
		mv.setViewName("sft/noticeModify");
		return mv;
	}

	// 게시글삭제작업
	@GetMapping("/noticeDelete")
	public String noticeDelete(HttpServletRequest req, Long bno, @ModelAttribute("pageVO") PageVO vo,
			RedirectAttributes rttr) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sft/noticeWrite");
		repo.deleteById(bno);
		log.info(vo + " ");
		rttr.addFlashAttribute("msg", "success");
		return "redirect:notice";
	}

	// 게시글글쓰기작업
	@PostMapping("/noticeWrite")
	public String noticeWrite(@ModelAttribute("vo") Notice vo, RedirectAttributes rttr) {
		log.info("==글쓰기작업==" + vo + " ");
		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:notice";
	}

	// 게시글수정작업
	@PostMapping("/noticeModify")
	public String noticeModify(@ModelAttribute("vo") Notice vo, RedirectAttributes rttr) {
		log.info("==수정작업==" + vo + " ");
		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:notice";
	}

}
