package fr.uge.myapplication.model;

import java.util.HashSet;
import java.util.Set;

public class Sru {

    private String name;
    private String pwd;
    private Set<PC> myPC;
    private Set<PC> myUpVote;
    private Set<PC> myDownVote;

    public Sru() {
    }

    public Sru(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
        this.myPC = new HashSet<>();
        this.myUpVote = new HashSet<>();
        this.myDownVote = new HashSet<>();
    }

    public Sru(String name, String pwd, Set<PC> myPC, Set<PC> myUpVote, Set<PC> myDownVote) {
        this.name = name;
        this.pwd = pwd;
        this.myPC = myPC;
        this.myUpVote = myUpVote;
        this.myDownVote = myDownVote;
    }

    @Override
    public String toString() {
        return "Sru{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Set<PC> getMyPC() {
        return myPC;
    }

    public void setMyPC(Set<PC> myPC) {
        this.myPC = myPC;
    }

    public Set<PC> getMyUpVote() {
        return myUpVote;
    }

    public void setMyUpVote(Set<PC> myUpVote) {
        this.myUpVote = myUpVote;
    }

    public Set<PC> getMyDownVote() {
        return myDownVote;
    }

    public void setMyDownVote(Set<PC> myDownVote) {
        this.myDownVote = myDownVote;
    }

    public void addPC(PC pc) {
        myPC.add(pc);
    }

    public void addPCUpVote(PC pc) {
        myUpVote.add(pc);
    }

    public void addPCDownVote(PC pc) {
        myDownVote.add(pc);
    }

    public boolean removePCDownVote(PC pc) {
        return myDownVote.remove(pc);
    }

    public boolean removePCUpVote(PC pc) {
        return myUpVote.remove(pc);
    }
}
