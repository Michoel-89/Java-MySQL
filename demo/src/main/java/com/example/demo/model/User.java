package com.example.demo.model;
import javax.persistence.*;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TodoItem> todoList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashedPassword;
    }

    public boolean isPasswordCorrect(String inputPassword) {
        return BCrypt.checkpw(inputPassword, this.password);
    }

    public List<TodoItem> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoItem> todoList) {
        this.todoList = todoList;
    }
}

