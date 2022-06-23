package com.study.model;

import org.springframework.web.multipart.MultipartFile;

public class BbsDTO {

	private int bbsno;
	private String wname;
	private String title;
	private String content;
	private String passwd;
	private int viewcnt;
	private String wdate;
	private int Grpno;
	private int Indent;
	private int Ansnum;
	private String filename;
	private int filesize;
	private MultipartFile filenameMF;//form 에서 서버로 보내지는 파일의 객체 타입

	

	@Override
  public String toString() {
    return "BbsDTO [bbsno=" + bbsno + ", wname=" + wname + ", title=" + title + ", content=" + content + ", passwd="
        + passwd + ", viewcnt=" + viewcnt + ", wdate=" + wdate + ", Grpno=" + Grpno + ", Indent=" + Indent + ", Ansnum="
        + Ansnum + ", filename=" + filename + ", filesize=" + filesize + ", filenameMF=" + filenameMF + "]";
  }



  public BbsDTO(int bbsno, String wname, String title, String content, String passwd, int viewcnt, String wdate,
      int grpno, int indent, int ansnum, String filename, int filesize, MultipartFile filenameMF) {
    super();
    this.bbsno = bbsno;
    this.wname = wname;
    this.title = title;
    this.content = content;
    this.passwd = passwd;
    this.viewcnt = viewcnt;
    this.wdate = wdate;
    Grpno = grpno;
    Indent = indent;
    Ansnum = ansnum;
    this.filename = filename;
    this.filesize = filesize;
    this.filenameMF = filenameMF;
  }



  public String getFilename() {
    return filename;
  }



  public void setFilename(String filename) {
    this.filename = filename;
  }



  public int getFilesize() {
    return filesize;
  }



  public void setFilesize(int filesize) {
    this.filesize = filesize;
  }



  public MultipartFile getFilenameMF() {
    return filenameMF;
  }



  public void setFilenameMF(MultipartFile filenameMF) {
    this.filenameMF = filenameMF;
  }



  public int getBbsno() {
		return bbsno;
	}



	public void setBbsno(int bbsno) {
		this.bbsno = bbsno;
	}



	public String getWname() {
		return wname;
	}



	public void setWname(String wname) {
		this.wname = wname;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPasswd() {
		return passwd;
	}



	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}



	public int getViewcnt() {
		return viewcnt;
	}



	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}



	public String getWdate() {
		return wdate;
	}



	public void setWdate(String wdate) {
		this.wdate = wdate;
	}



	public int getGrpno() {
		return Grpno;
	}



	public void setGrpno(int grpno) {
		Grpno = grpno;
	}



	public int getIndent() {
		return Indent;
	}



	public void setIndent(int indent) {
		Indent = indent;
	}



	public int getAnsnum() {
		return Ansnum;
	}



	public void setAnsnum(int ansnum) {
		Ansnum = ansnum;
	}



	public BbsDTO() {
		
	}

	

	
	
	
}
