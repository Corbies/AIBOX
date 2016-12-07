package com.wx.aibox.model;

import java.io.Serializable;

/**
 * Created by cxj on 2016/12/6.
 */
public class QaModel implements Serializable{
    private int id;
   private String problem;
    private String  answer;
    private String  email;
    private  String username;

    @Override
    public String toString() {
        return "QaModel{" +
                "id=" + id +
                ", problem='" + problem + '\'' +
                ", answer='" + answer + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
