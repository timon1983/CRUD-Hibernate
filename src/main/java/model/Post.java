package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private long id;
    @Column(name = "content", length = 45, nullable = false)
    private String content;
    @Column(name = "created",nullable = false)
    private long created;
    @Column(name = "updated",nullable = false)
    private long updated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String content, long created, long updated) {
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return created == post.created &&
                updated == post.updated &&
                content.equals(post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, created, updated);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
