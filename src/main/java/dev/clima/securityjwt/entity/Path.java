package dev.clima.securityjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Path {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private HttpMethod httpMethod;

    @ManyToMany(mappedBy = "paths")
    private Collection<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "paths")
    private Collection<Privilege> privileges = new HashSet<>();
}
