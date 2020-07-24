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

import com.sft.nonhyeon.dto.Consulting;
import com.sft.nonhyeon.persistence.ConsultingRepositoty;
import com.sft.nonhyeon.util.Common;
import com.sft.nonhyeon.vo.PageMaker;
import com.sft.nonhyeon.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@Log
public class ConsultingController {
	@Autowired
	ConsultingRepositoty repo;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/consulting")
	public ModelAndView view(String bno, @ModelAttribute("pageVO") PageVO vo,HttpServletRequest req){
		ModelAndView mv=new ModelAndView();
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<Consulting> result=repo.findAll(repo.makePredicate(null, null),pageable);
		mv.addObject("consulting",new PageMaker(result));
		log.info("BNO: "+ bno);
		Common.toPrintIp(req);
		mv.setViewName("sft/consulting");
		return mv;
	}
	
	@PostMapping("/consultingWrite")
	public String ConsultingWrite(@ModelAttribute("vo")Consulting vo, RedirectAttributes rttr){
		repo.save(vo);
		log.info(vo+" ");
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/";
	}

}
