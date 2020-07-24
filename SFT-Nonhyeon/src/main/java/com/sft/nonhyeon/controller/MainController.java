package com.sft.nonhyeon.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sft.nonhyeon.dto.MembershipStatus;
import com.sft.nonhyeon.util.Common;

import lombok.extern.java.Log;

@Controller
@Log
public class MainController {
	// 메인화면
		@GetMapping("/")
		public ModelAndView mainHome(HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();

			// 공통코드
			commonCode(mv, req);

			log.info("홈화면");
			
			//실시간가입현황
			realTimeSubStatus(mv);
			mv.setViewName("main/home");
			return mv;
		}
		
		
		@GetMapping("/sft")
		public ModelAndView sft(Model model, HttpServletRequest req) {
			ModelAndView mv=new ModelAndView();
			
			String url = String.valueOf(req.getParameter("page"));
			String page = "sft/" + url;


			mv.addObject("COMPANY_NAME", Common.COMPANY_NAME);
			mv.setViewName(page);
			Common.toPrintIp(req);
			return mv;
			
		}
		
		// 검색허용
		@GetMapping("/robots.txt")
		public ModelAndView robots(HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("main/robots"); // 뷰의 이름
			String str = "User-agent: *<br>" + "Allow: /";
			mv.addObject("str", str); // 뷰로 보낼 데이터 값
			// 공통코드
			commonCode(mv, req);
			return mv;
		}
		
		
		
		private void commonCode(ModelAndView mv, HttpServletRequest req) {

			log.info("=========실제 메뉴 이동로직==========");
			Enumeration<String> names=req.getParameterNames();
			while(names.hasMoreElements()) {
				String name = (String)names.nextElement();
				log.info(name + " : " +req.getParameter(name));
			}
			log.info("============================");
			
			mv.addObject("COMPANY_NAME", Common.COMPANY_NAME);
			mv.addObject("TODAY", Common.getKrThisMonthDay());
			// 어드민권한체크
			String admin = String.valueOf(req.getParameter("SFT"));
			if ("admin".equals(admin)) {
				mv.addObject("admin", "Y");
			}
			
			

			Common.toPrintIp(req);
		}
		
		
		
		private void realTimeSubStatus(ModelAndView mv) {
			String[] strs1= {"차*영","김*호","김*훈","전*문","신*환","이*호","이*름","박*성","이*표","구*철"};
			String[] strs2= {"라*수","남*우","이*욱","장*만","김*환","최*훈","강*름","차*름","정*호","김*철"};
			String[] strs3= {"김*기","서*리","김*호","이*원","서*환","채*호","최*아","하*성","이*호","서*철"};
			String[] strs4= {"호*기","사*진","라*수","이*만","김*환","강*호","임*정","김*성","곽*진","사*철"};
			String[] strs5= {"미*란","곽*호","정*건","차*열","김*중","김*하","장*정","김*훈","김*철","곽*철"};
			String[] strs6= {"김*채","황*호","김*후","최*현","김*원","최*후","고*수","정*아","강*양","여*철"};
			String[] strs7= {"하*영","하*진","김*석","하*호","신*환","최*락","민*름","표*름","강*표","이*철"};
			String[] strs8= {"차*영"};
			String[] strs;
			String yoil=Common.getyoil();
			switch (yoil) {
			case "월":
				 strs= strs1;
				break;
			case "화":
				 strs= strs2;
				break;
			case "수":
				 strs= strs3;
				break;
			case "목":
				 strs= strs4;
				break;
			case "금":
				 strs= strs5;
				break;
			case "토":
				 strs= strs6;
				break;
			case "일":
				 strs= strs7;
				break;
			default:
				strs= strs8;
				break;
			}
			List<MembershipStatus> list=new ArrayList<MembershipStatus>();
			for(int i=0;i<strs.length;i++) {
				MembershipStatus membershipStatus=new MembershipStatus();
				membershipStatus.setComments(strs[i]+"님이 가입요청 하셨습니다.");
				membershipStatus.setTime(Common.getKrThisMonthDay());
				if(i==6) {
					membershipStatus.setStatus("02");
				}else {
					membershipStatus.setStatus("01");
				}
				list.add(membershipStatus);
			}
			
			
			switch (yoil) {
			case "일":
				 strs= strs1;
				break;
			case "토":
				 strs= strs2;
				break;
			case "금":
				 strs= strs3;
				break;
			case "월":
				 strs= strs4;
				break;
			case "수":
				 strs= strs5;
				break;
			case "화":
				 strs= strs6;
				break;
			case "목":
				 strs= strs7;
				break;
			default:
				strs= strs8;
				break;
			}
			
			List<MembershipStatus> list2=new ArrayList<MembershipStatus>();
			for(int i=0;i<strs.length;i++) {
				MembershipStatus membershipStatus=new MembershipStatus();
				membershipStatus.setComments(strs[i]+"님 문의사항");
				membershipStatus.setTime(Common.getKrThisMonthDay());
				if(i==3) {
					membershipStatus.setStatus("02");
				}else {
					membershipStatus.setStatus("01");
				}
				list2.add(membershipStatus);
			}
			
			
			
			mv.addObject("MembershipStatus",list);
			mv.addObject("RealStatus",list2);
		}
		
}
