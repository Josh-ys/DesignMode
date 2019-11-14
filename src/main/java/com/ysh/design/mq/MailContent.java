package com.ysh.design.mq;

/**
 * 邮件实体
 *
 * @author joeysh
 * @date 2018/08/23 00:59
 */
public class MailContent {
    private Integer id;
    private String title;
    private String content;

    public MailContent(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MailContent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
