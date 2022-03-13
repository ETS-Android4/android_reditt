package fr.uge.myapplication.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PC {
    private long id;
    private Sru author;
    private PC parent;
    private Category category;
    private Set<Sru> votersUp;
    private Set<Sru> votersDown;
    private Date date;
    private String title;
    private String content;
    private long upVote;
    private long downVote;

    public PC(Sru author, String content, String title, PC parent, Set<Sru> votersUp,
              Set<Sru> votersDown, Category category, Date date) {
        this.author = author;
        this.content = content;
        this.title = title;
        this.parent = parent;
        this.upVote = votersUp.size();
        this.downVote = votersDown.size();
        this.votersUp = votersUp;
        this.votersDown = votersDown;
        this.category = category;
        this.date = date;
        addMapping(author, category, votersUp, votersDown);
    }

    public PC(Sru author, String title, String content, PC parent, Category category, Date date) {
        this.author = author;
        this.parent = parent;
        this.category = category;
        this.date = date;
        this.title = title;
        this.content = content;
        this.upVote = 0;
        this.downVote = 0;
        this.votersUp = new HashSet<>();
        this.votersDown = new HashSet<>();
        addMapping(author, category, votersUp, votersDown);
    }

    private void addMapping(Sru author, Category category, Set<Sru> votersUp, Set<Sru> votersDown) {
        author.addPC(this);
        category.addPC(this);
        votersUp.forEach(elem -> elem.addPCUpVote(this));
        votersDown.forEach(elem -> elem.addPCDownVote(this));
    }

    @Override
    public String toString() {
        return "PC{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                '}';
    }

    /**
     * use to see all the voters for this post or comment
     *
     * @return set of all voters
     */
    public Set<Sru> getVoters() {
        Set<Sru> resp = new HashSet<>();
        resp.addAll(votersDown);
        resp.addAll(votersUp);
        return resp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sru getAuthor() {
        return author;
    }

    public void setAuthor(Sru author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PC getParent() {
        return parent;
    }

    public void setParent(PC parent) {
        this.parent = parent;
    }

    public long getUpVote() {
        return upVote;
    }

    public void setUpVote(long upVote) {
        this.upVote = upVote;
    }

    public long getDownVote() {
        return downVote;
    }

    public void setDownVote(long downVote) {
        this.downVote = downVote;
    }

    public Set<Sru> getVotersUp() {
        return votersUp;
    }

    public void setVotersUp(Set<Sru> votersUp) {
        this.votersUp = votersUp;
        this.upVote = votersUp.size();
    }

    public Set<Sru> getVotersDown() {
        return votersDown;
    }

    public void setVotersDown(Set<Sru> votersDown) {
        this.votersDown = votersDown;
        this.downVote = votersDown.size();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addUpVoter(Sru voterUp) {
        if (this.votersUp.add(voterUp)) this.upVote += 1L;
    }

    public void removeUpVoter(Sru voterUp) {
        if (this.votersUp.remove(voterUp)) this.upVote -= 1L;
    }

    public void addDownVoter(Sru voterDown) {
        if (this.votersDown.add(voterDown)) this.downVote += 1L;
    }

    public void removeDownVoter(Sru voterDown) {
        if (this.votersDown.remove(voterDown)) this.downVote -= 1L;
    }

    public void removeAllVotes() {
        this.votersUp.clear();
        this.votersDown.clear();
        this.upVote = 0;
        this.downVote = 0;
    }
}
