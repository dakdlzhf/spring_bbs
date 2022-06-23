package com.study.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.model.BbsServiceImpl")
public class BbsServiceImpl implements BbsService {

  @Autowired
  private BbsMapper mapper;
  
  @Autowired
  private ReplyMapper rmapper;
  
  //목록 조회
  public List<BbsDTO> list(Map map) {
    // TODO Auto-generated method stub
    return mapper.list(map);
  }
  //검색
  public int total(Map map) {

    return mapper.total(map);
  }
  //조회 카운트 증가
  public void upViewcnt(int bbsno) {
    mapper.upViewcnt(bbsno);
    
  }
  //조회
  public BbsDTO read(int bbsno) {
    return mapper.read(bbsno);
  }
  //생성
  public int create(BbsDTO dto) {
    return mapper.create(dto);
  }
  //비번체크
  public int passCheck(Map map) {
    return mapper.passCheck(map);
  }
  //수정
  public int update(BbsDTO dto) {
    return mapper.update(dto);
  }
  //삭제
  public void delete(int bbsno) {
   rmapper.bdelete(bbsno);
   mapper.delete(bbsno);
  }
  //답변일기
  public BbsDTO readReply(int bbsno) {
    
    return mapper.readReply(bbsno);
  }
  //Ansnum 증가
  public void upAnsnum(Map map) {
    mapper.upAnsnum(map);
    
  }
  //답변생성
  public int createReply(BbsDTO dto) {
    
    return mapper.createReply(dto);
  }
  //답변 체크
  public int checkRefnum(int bbsno) {
    
    return mapper.checkRefnum(bbsno);
  }

}
