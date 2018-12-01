package sr.unasat.jpa.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "city")
    private Set<McDonalds> mcDonalds = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<McDonalds> getMcDonalds() {
        return mcDonalds;
    }

    public void setMcDonalds(Set<McDonalds> mcDonalds) {
        this.mcDonalds = mcDonalds;
    }

    public void addMcDonalds(McDonalds mcDonalds) {
        if (mcDonalds.getCity() != this) {
            mcDonalds.setCity(this);
        }
        if (!this.mcDonalds.contains(mcDonalds)) {
            this.mcDonalds.add(mcDonalds);
        }
    }

    public void removeMcDonalds(McDonalds mcDonalds) {
        if (mcDonalds.getCity() == this) {
            mcDonalds.setCity(null);
        }
        if (this.mcDonalds.contains(mcDonalds)) {
            this.mcDonalds.remove(mcDonalds);
        }
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
