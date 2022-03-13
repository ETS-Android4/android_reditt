package fr.uge.myapplication.model;

import java.util.HashSet;
import java.util.Set;

public class Category {

    private String label;
    Set<PC> posts;

    public Category() {
    }

    public Category(String label, Set<PC> pcSet) {
        this.label = label;
        this.posts = pcSet;
    }

    public Category(String label) {
        this.label = label;
        this.posts = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Category{" +
                "label='" + label + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<PC> getPosts() {
        return posts;
    }

    public void setPosts(Set<PC> posts) {
        this.posts = posts;
    }

    public void addPC(PC pc) {
        this.posts.add(pc);
    }
}
