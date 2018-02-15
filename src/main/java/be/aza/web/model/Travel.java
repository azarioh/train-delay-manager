package be.aza.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@NamedQueries({
    @NamedQuery(
            name = Travel.FIND_BY_NAME,
            query = "select travel from Travel travel where name = :name order by travel.name"
    ),
    @NamedQuery(
            name = Travel.FIND_ALL,
            query = "select travel from Travel travel order by travel.name"
    )
})
public @Data class Travel {

	public static final String FIND_BY_NAME = "Travel.findByName";
	public static final String FIND_ALL = "Travel.findAll";
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    @Column
    private String departure;

    @Column
    private String destination;
    
}