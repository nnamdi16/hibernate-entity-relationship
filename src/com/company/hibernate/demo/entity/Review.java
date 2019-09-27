package com.company.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
  //Define the fields
  //Annotate the fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  
  @Column(name = "comment")
  private String comment;
  
  @ManyToOne
  private Course course;
  
  //Define constructor
  public Review() {
  }
  
  public Review(String comment) {
    this.comment = comment;
  }
  
  //Define our getters and setters
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getComment() {
    return comment;
  }
  
  public void setComment(String comment) {
    this.comment = comment;
  }
  
  public Course getCourse() {
    return course;
  }
  
  public void setCourse(Course course) {
    this.course = course;
  }
  
  @Override
  public String toString() {
    return "Review{" +
            "id=" + id +
            ", comment='" + comment + '\'' +
            '}';
  }
}