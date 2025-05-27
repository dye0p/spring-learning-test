package cholog;

public class Todo {

    private Long id;

    private Long userId;

    private String title;

    private Boolean completed;

    // TODO: Todo 객체가 가지는 필드들을 정의

    //    userId, id, title, completed
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return this.title;
    }

    public Boolean getCompleted() {
        return completed;
    }
}
