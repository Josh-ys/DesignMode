package com.ysh.design.masterworker;

/**
 * @author joeysh
 * @date 2018/08/23 23:32
 */
public class Task {
    private Integer workId;
    private String workContent;


    @Override
    public String toString() {
        return "Task{" +
                "workId=" + workId +
                ", workContent='" + workContent + '\'' +
                '}';
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }
}
