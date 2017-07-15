package com.example.administrator.greendao_demo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017-06-02.
 */
@Entity
public class Teacher {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String project;
    @Generated(hash = 1532895738)
    public Teacher(Long id, String name, String project) {
        this.id = id;
        this.name = name;
        this.project = project;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProject() {
        return this.project;
    }
    public void setProject(String project) {
        this.project = project;
    }
}
