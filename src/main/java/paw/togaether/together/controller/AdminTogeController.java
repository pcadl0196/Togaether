package paw.togaether.together.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import paw.togaether.common.domain.CommandMap;
import paw.togaether.together.service.TogetherAdminService;

@Controller
public class AdminTogeController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="togetherAdminService")
	private TogetherAdminService togetherAdminService;
	
	/* 23.01.25 박선영 관리자 게시글 리스트 출력
	 * 23.02.01 박선영 관리자 서비스 분리 */
	@RequestMapping(value="/admin/together/list")
	public ModelAndView togetherAdminList(CommandMap commandMap) throws Exception {
		
		ModelAndView mv = new ModelAndView("/admin/together/togeAdminList");
		
		System.out.println(commandMap.getMap());//값을 잘 받아오는지 확인
		
		List<Map<String, Object>> list = togetherAdminService.togetherAdminList(commandMap.getMap());
		
		mv.addObject("list", list);
		
		return mv;
	}
	
	/* 23.01.25 박선영 관리자 게시글 카테고리 리스트 출력 */
	@RequestMapping(value="/admin/together/catelist")
	public ModelAndView togetherAdminCate(CommandMap commandMap) throws Exception {
	
		ModelAndView mv = new ModelAndView("/admin/together/togeAdminCateList");
		
		System.out.println(commandMap.getMap());
		
		List<Map<String, Object>> catelist = togetherAdminService.togetherAdminCate(commandMap.getMap());
		
		mv.addObject("catelist", catelist);
		
		return mv;
	}
	
	/* 23.01.25 박선영 관리자 카테고리 등록폼 */
	@RequestMapping(value="/admin/together/catewriteForm")
	public ModelAndView openAdminTogeCateWrite(CommandMap commandMap) throws Exception {
		
		ModelAndView mv = new ModelAndView("/admin/together/togeAdminCateWrite");
		
		return mv;
	}
	
	/* 23.01.25 박선영 관리자 카테고리 등록 */
	@RequestMapping(value="/admin/together/catewrite")
	public ModelAndView adminTogeCateWrite(CommandMap commandMap) throws Exception {
		
		System.out.println(commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("redirect:/admin/together/catelist.paw");
		
		togetherAdminService.adminTogeCateWrite(commandMap.getMap());
		
		return mv;
	}
	
	/* 23.02.06 박선영 관리자 견종몸무게 리스트 */
	@RequestMapping(value="/admin/together/wtlist")
	public ModelAndView adminWeightList(CommandMap commandMap) throws Exception {
	
		ModelAndView mv = new ModelAndView("/admin/together/togeAdminWtList");
		
		System.out.println(commandMap.getMap());
		
		List<Map<String, Object>> wtlist = togetherAdminService.adminWeightList(commandMap.getMap());
		
		mv.addObject("wtlist", wtlist);
		
		return mv;
	}
	
	/* 23.02.03 박선영 관리자 견종몸무게 등록폼 */
	@RequestMapping(value="/admin/together/weightwriteForm")
	public ModelAndView openadminTogeWeight(CommandMap commandMap) throws Exception {
		
		ModelAndView mv = new ModelAndView("/admin/together/togeAdminWeightWrite");
		
		return mv;
	}
	
	/* 23.02.03 박선영 관리자 견종몸무게 등록 */
	@RequestMapping(value="/admin/together/weightwrite")
	public ModelAndView adminTogeWeight(CommandMap commandMap) throws Exception {
		
		System.out.println(commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("redirect:/admin/together/wtlist");
		
		togetherAdminService.adminTogeWeight(commandMap.getMap());
		
		return mv;
	}
	
	/* 23.02.14 박선영 관리자 게시글 상세보기 */
	@RequestMapping(value="/admin/together/detail/{TO_IDX}")
	public ModelAndView adminTogeDetail (@PathVariable("TO_IDX") int TO_IDX, CommandMap commandMap)throws Exception {
		
		System.out.println(TO_IDX);
		System.out.println(commandMap.getMap());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//날짜 형태 정해주기
		SimpleDateFormat tsdf = new SimpleDateFormat("HH:mm");//시간 형태 정해주기
		
		Date now = new Date();//날짜 마감에 따른 참여하기 비활성화 목적
		
		String nowDate = sdf.format(now);
		String nowTime = tsdf.format(now);
		
		ModelAndView mv = new ModelAndView("/admin/together/togeAdminDetail");
		
		Map<String, Object> map = togetherAdminService.adminTogeDetail(commandMap.getMap());//상세보기 정보
		List<Map<String, Object>> wlist = togetherAdminService.togetherAdminWithList(commandMap.getMap());//참여자리스트
		
		mv.addObject("nowTime", nowTime);
		mv.addObject("wlist", wlist);
		mv.addObject("nowDate", nowDate);
		mv.addObject("map", map);
		
		return mv;
	}
	
	/* 23.02.14 박선영 관리자 게시글 삭제 */
	@RequestMapping(value="/admin/together/delete")
	public ModelAndView adminTogeDel(CommandMap commandMap) throws Exception {
		
		System.out.println(commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("redirect:/admin/together/list");
		
		togetherAdminService.adminTogeDel(commandMap.getMap());
		
		return mv;
	}

}
