package cn.sheetanchor.sparrow.demo.model;


import cn.sheetanchor.common.persistence.DataEntity;

/**
 * @Author: 阁楼麻雀
 * @Date: 2017/8/14 上午11:16
 * @Desc: 第一步生成model，如果为列表页继承DataEntity
 */

public class Demo extends DataEntity<Demo> {

  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private String phone;
  private String sex;
  private long age;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }

}
