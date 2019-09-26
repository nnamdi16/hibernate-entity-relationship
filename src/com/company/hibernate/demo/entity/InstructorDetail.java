package com.company.hibernate.demo.entity;

import javax.persistence.*;

//Annotate the class as entity
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {
    //Define the fields
    //Annotate the fields with db column names
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;
  
  //BI_DIRECTIONAL IMPLEMENTATION
    //Add new field for instructor (also add getters/setters)
    //Add OneToOne annotation (bi-directional)
//    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
  //With bi-direction you can load the info from the child and use that information to access the information from the parent.
    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //mappedBy = "instructorDetail" refers/points to the instructor details property(instructorDetails)  in the instructor class.
    //It means the instructor property is mapped by the instructorDetails property in the Instructor class.
    //Mapped by  tell Hibernate to look at the instructorDetails property in the Instructor class, use the information from the
    // instructor class @JoinColumn to help find the associated  instructor.
    private Instructor instructor;

    public InstructorDetail() {

    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
