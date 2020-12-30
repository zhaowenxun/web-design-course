package com.staffmanage.entity;


public class Post {

  private String pnum;
  private String pname;
  private long authorizedStrength;
  private String type;


  public String getPnum() {
    return pnum;
  }

  public void setPnum(String pnum) {
    this.pnum = pnum;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public long getAuthorizedStrength() {
    return authorizedStrength;
  }

  public void setAuthorizedStrength(long authorizedStrength) {
    this.authorizedStrength = authorizedStrength;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
