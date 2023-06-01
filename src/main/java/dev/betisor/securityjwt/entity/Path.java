package dev.betisor.securityjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
@Builder
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
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "paths")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Privilege> privileges = new HashSet<>();

    @ManyToMany(mappedBy = "paths")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<AccessRule> accessRules = new HashSet<>();
}
