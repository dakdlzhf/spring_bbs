package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bbs.UploadBbs;
import com.study.model.BbsDTO;
import com.study.model.BbsService;
import com.study.model.ReplyService;
import com.study.utility.Utility;

@Controller
public class BbsController {

  @Autowired
  @Qualifier("com.study.model.BbsServiceImpl")
  private BbsService dao;

  @Autowired
  @Qualifier("com.study.model.ReplyServiceImpl")
  private ReplyService rservice;

  @PostMapping("/bbs/reply")
  public String reply(BbsDTO dto) {

    String upDir = UploadBbs.getUploadDir();
    if (dto.getFilenameMF().getSize() > 0) {
      dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir));
      dto.setFilesize((int) dto.getFilenameMF().getSize());
    }

    Map map = new HashMap();
    map.put("grpno", dto.getGrpno());
    map.put("ansnum", dto.getAnsnum());
    dao.upAnsnum(map);
    if (dao.createReply(dto) == 1) {
      return "redirect:list";
    } else {
      return "error";
    }

  }

  @GetMapping("/bbs/reply/{bbsno}")
  public String reply(@PathVariable int bbsno, Model model) {
    model.addAttribute("dto", dao.readReply(bbsno));

    return "/reply";
  }

  @PostMapping("/bbs/delete")
  public String delete(int bbsno, String passwd, String oldfile) {
    Map map = new HashMap();
    map.put("bbsno", bbsno);
    map.put("passwd", passwd);

    String upDir = UploadBbs.getUploadDir();

    int pflag = dao.passCheck(map);
    String url = "./passwdError";

    if (pflag == 1) {
      try {
        dao.delete(bbsno);
        url = "redirect:./list";
        if (oldfile != null)
          Utility.deleteFile(upDir, oldfile);
      } catch (Exception e) {
        e.printStackTrace();
        url = "./error";
      }
    }

    return url;
  }

  @GetMapping("/bbs/delete/{bbsno}")
  public String delete(@PathVariable int bbsno, String oldfile, Model model) {

    int cnt = dao.checkRefnum(bbsno);
    boolean flag = false;
    if (cnt > 0)
      flag = true;

    model.addAttribute("bbsno", bbsno);
    model.addAttribute("oldfile", oldfile);
    model.addAttribute("flag", flag);

    return "/delete";
  }

  @PostMapping("/bbs/update")
  public String update(BbsDTO dto, String oldfile) {
    String upDir = UploadBbs.getUploadDir();
    if (dto.getFilenameMF().getSize() > 0) {
      if (oldfile != null && !oldfile.equals(""))
        Utility.deleteFile(upDir, oldfile);
      dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir));
      dto.setFilesize((int) dto.getFilenameMF().getSize());
    }

    Map map = new HashMap();
    map.put("bbsno", dto.getBbsno());
    map.put("passwd", dto.getPasswd());
    int pflag = dao.passCheck(map);
    int flag = 0;

    if (pflag == 1)
      flag = dao.update(dto);

    if (pflag != 1)
      return "passwdError";
    else if (flag != 1)
      return "error";
    else {
      return "redirect:list";
    }
  }

  @GetMapping("/bbs/update/{bbsno}")
  public String update(@PathVariable int bbsno, Model model) {
    model.addAttribute("dto", dao.read(bbsno));
    return "/update";
  }

  @GetMapping("/")
  public String home(Locale locale, Model model) {

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate);
    return "/home";
  }

  @GetMapping("/bbs/list")
  public String list(HttpServletRequest request) {
    // 검색관련--------------
    String col = Utility.checkNull(request.getParameter("col"));
    String word = Utility.checkNull(request.getParameter("word"));

    if (col.equals("total"))
      word = "";

    // 페이징 관련---------------
    int nowPage = 1;
    if (request.getParameter("nowPage") != null) {
      nowPage = Integer.parseInt(request.getParameter("nowPage"));
    }

    int recordPerPage = 5;

    int sno = ((nowPage - 1) * recordPerPage);
    int eno = recordPerPage;

    // 1.model 사용
    Map map = new HashMap();// sno,eno,col,word
    map.put("sno", sno);
    map.put("eno", eno);
    map.put("col", col);
    map.put("word", word);

    List<BbsDTO> list = dao.list(map);
    int total = dao.total(map);
    String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
    // 2.request 저장 (view페이지에서 사용할 내용을 저장)
    request.setAttribute("list", list);
    request.setAttribute("paging", paging);
    request.setAttribute("col", col);
    request.setAttribute("word", word);
    request.setAttribute("nowPage", nowPage);
    request.setAttribute("rservice", rservice);

    return "/list";
  }

  @PostMapping("/bbs/create")
  public String create(BbsDTO dto) {

    String upDir = UploadBbs.getUploadDir();

    if (dto.getFilenameMF().getSize() > 0) {// 브라우저에서 파일을 보냈다.
      dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir));
      dto.setFilesize((int) dto.getFilenameMF().getSize());
    }

    int cnt = dao.create(dto);
    if (cnt != 1)
      return "error";
    return "redirect:list";
  }

  @GetMapping("/bbs/create")
  public String create() {

    return "/create";
  }

  @GetMapping("/bbs/read/{bbsno}")
  public String read(@PathVariable int bbsno, Model model, HttpServletRequest request) {
    dao.upViewcnt(bbsno);
    model.addAttribute("dto", dao.read(bbsno));
    /* 댓글 관련 시작 */
    int nPage = 1;
    if (request.getParameter("nPage") != null) {
      nPage = Integer.parseInt(request.getParameter("nPage"));
    }
    int recordPerPage = 3;

    int sno = (nPage - 1) * recordPerPage;
    int eno = recordPerPage;

    Map map = new HashMap();
    map.put("sno", sno);
    map.put("eno", eno);
    map.put("nPage", nPage);

    model.addAllAttributes(map);

    /* 댓글 처리 끝 */

    return "/read";
  }

  @PostMapping(value = "/bbs/delete_Ajax", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Map<String, String> delete_Ajax(@RequestBody BbsDTO dto, HttpServletRequest request) {
    boolean cflag = false;
    int cnt = dao.checkRefnum(dto.getBbsno());
    if (cnt > 0)
      cflag = true; // 부모글
    String upDir = UploadBbs.getUploadDir();
    Map map = new HashMap();
    map.put("bbsno", dto.getBbsno());
    map.put("passwd", dto.getPasswd());

    boolean pflag = false;
    boolean flag = false;

    if (!cflag) {
      int cnt2 = dao.passCheck(map);
      if (cnt2 > 0)
        pflag = true;
    }
    if (pflag) {
      if (dto.getFilename() != null)
        Utility.deleteFile(upDir, dto.getFilename());
      // int cnt3 = dao.delete(dto.getBbsno());
      // if (cnt3 > 0)
      // flag = true;
    }

    Map<String, String> map2 = new HashMap<String, String>();

    if (cflag) {
      map2.put("str", "답변있는 글이므로 삭제할 수 없습니다");
      map2.put("color", "blue");
    } else if (!pflag) {
      map2.put("str", "패스워드가 잘못입력되었습니다");
      map2.put("color", "blue");
    } else if (flag) {
      map2.put("str", "삭제 처리되었습니다");
      map2.put("color", "blue");
    } else {
      map2.put("str", "삭제중 에러가 발생했습니다");
      map2.put("color", "blue");
    }

    return map2;
  }

  @GetMapping("/bbs/delete_Ajax")
  public String delete_Ajax() {

    return "/bbs/delete_Ajax";
  }

  @GetMapping("/bbs/fileDown")
  public void fileDown(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // 저장 폴더를 절대 경로로 변환
    String dir = UploadBbs.getUploadDir();
    // 파일명 받기
    String filename = request.getParameter("filename");
    byte[] files = FileUtils.readFileToByteArray(new File(dir, filename));
    response.setHeader("Content-disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
    // Content-Transfer-Encoding : 전송 데이타의 body를 인코딩한 방법을 표시함.
    response.setHeader("Content-Transfer-Encoding", "binary");
    /**
     * Content-Disposition가 attachment와 함게 설정되었다면 'Save As'로 파일을 제안하는지 여부에 따라 브라우저가
     * 실행한다.
     */
    response.setContentType("application/octet-stream");
    response.setContentLength(files.length);
    response.getOutputStream().write(files);
    response.getOutputStream().flush();
    response.getOutputStream().close();
  }

}
