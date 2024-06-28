package org.koreait;

public class Motivation {
    private int id;
    private String body;
    private String source;

    Motivation (int lastId, String body, String source) {
        this.id = lastId;
        this.body = body;
        this.source = source;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
