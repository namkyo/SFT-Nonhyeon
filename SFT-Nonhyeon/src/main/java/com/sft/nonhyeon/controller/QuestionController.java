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

import com.sft.nonhyeon.dto.Question;
import com.sft.nonhyeon.persistence.QuestionRepositoty;
import com.sft.nonhyeon.util.Common;
import com.sft.nonhyeon.vo.PageMaker;
import com.sft.nonhyeon.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@Log
public class QuestionController {
	@Autowired
	private QuestionRepositoty repo;

	// 게시판리스트 이동
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/question")
	public ModelAndView view(String bno, @ModelAttribute("pageVO") PageVO vo, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<Question> result = repo.findAll(repo.makePredicate(null, null), pageable);
		mv.addObject("question", new PageMaker(result));
		log.info("BNO: " + bno);
		Common.toPrintIp(req);
		mv.setViewName("sft/Question");
		return mv;
	}

	// 게시판글쓰기화면이동
	@GetMapping("/questionWrite")
	public ModelAndView writerView(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		Common.toPrintIp(req);
		mv.setViewName("sft/questionWrite");
		return mv;
	}

	// 게시글 읽기화면 이동
	@GetMapping("/questionView")
	public ModelAndView QuestionView(HttpServletRequest req, Long bno, @ModelAttribute("pageVO") PageVO vo) {
		ModelAndView mv = new ModelAndView();
		Common.toPrintIp(req);
		log.info("BNO: " + bno);
		repo.findById(bno).ifPresent(board -> mv.addObject("vo", board));
		mv.setViewName("sft/questionView");
		return mv;
	}

	// 게시글수정화면 이동
	@GetMapping("/questionModify")
	public ModelAndView QuestionModifyView(HttpServletRequest req, Long bno, @ModelAttribute("pageVO") PageVO vo) {
		ModelAndView mv = new ModelAndView();
		Common.toPrintIp(req);
		log.info("BNO: " + bno);
		repo.findById(bno).ifPresent(board -> mv.addObject("vo", board));
		mv.setViewName("sft/questionModify");
		return mv;
	}

	// 게시글삭제작업
	@GetMapping("/questionDelete")
	public String QuestionDelete(HttpServletRequest req, Long bno, @ModelAttribute("pageVO") PageVO vo,
			RedirectAttributes rttr) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sft/QuestionWrite");
		repo.deleteById(bno);
		log.info(vo + " ");
		rttr.addFlashAttribute("msg", "success");
		return "redirect:question";
	}

	// 게시글글쓰기작업
	@PostMapping("/questionWrite")
	public String QuestionWrite(@ModelAttribute("vo") Question vo, RedirectAttributes rttr) {
		log.info("==글쓰기작업==" + vo + " ");
		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:question";
	}

	// 게시글수정작업
	@PostMapping("/questionModify")
	public String QuestionModify(@ModelAttribute("vo") Question vo, RedirectAttributes rttr) {
		log.info("==수정작업==" + vo + " ");
		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:question";
	}

}
