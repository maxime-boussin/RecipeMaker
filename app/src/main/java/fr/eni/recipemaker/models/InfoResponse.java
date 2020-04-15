package fr.eni.recipemaker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoResponse implements Serializable {

    private String q;
    private int count;
    private String more;
    private ArrayList<Hits> hits;

    public InfoResponse(String q, String more, int count, ArrayList<Hits> hits) {
        this.q = q;
        this.count = count;
        this.more = more;
        this.hits = hits;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public ArrayList<Hits> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Hits> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("InfoResponse{");
        sb.append("q='").append(q).append('\'');
        sb.append(", count=").append(count);
        sb.append(", more='").append(more).append('\'');
        sb.append(", hits=").append(hits);
        sb.append('}');
        return sb.toString();
    }
}
