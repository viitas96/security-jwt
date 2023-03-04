package dev.clima.securityjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessRule extends AbstractEntity {

    private String rule;

    @ManyToMany()
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "access_rule_paths",
            joinColumns = @JoinColumn(name = "privileges_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "path_id", referencedColumnName = "id")
    )
    private Collection<Path> paths = new HashSet<>();

}
