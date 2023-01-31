package spring.example.spring;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private String age;
    private String name;

    public String getAge(User user) {
        return "18";
    }
}
