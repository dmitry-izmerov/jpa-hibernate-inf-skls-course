package ru.demi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "market")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketId;
    private String marketName;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "currency_name", referencedColumnName = "name"),
        @JoinColumn(name = "country_name", referencedColumnName = "country_name")
    })
    private Currency currency;
}
