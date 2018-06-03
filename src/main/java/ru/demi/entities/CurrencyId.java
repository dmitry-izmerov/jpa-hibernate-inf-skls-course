package ru.demi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.IdClass;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyId implements Serializable {
    private String name;
    private String countryName;
}
