package com.sft.nonhyeon.vo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sft.nonhyeon.dto.Notice;

public interface BoardController {
	//게시판리스트 이동
	public ModelAndView view(String bno, @ModelAttribute("pageVO") PageVO vo, HttpServletRequest req);
	//게시판글쓰기화면이동
	public ModelAndView writerView(HttpServletRequest req);
	//게시글 읽기화면 이동
	public ModelAndView noticeView(HttpServletRequest req,Long bno, @ModelAttribute("pageVO") PageVO vo);
	//게시글수정화면 이동
	public ModelAndView noticeModifyView(@ModelAttribute("vo")Notice vo, RedirectAttributes rttr);

	//게시글삭제작업
	public String noticeDelete(HttpServletRequest req,Long bno, @ModelAttribute("pageVO") PageVO vo,RedirectAttributes rttr);
	//게시글쓰기작업
	public String noticeWrite(@ModelAttribute("vo")Notice vo, RedirectAttributes rttr);
	//게시글수정작업
	public String noticeModify(@ModelAttribute("vo")Notice vo, RedirectAttributes rttr);
}
