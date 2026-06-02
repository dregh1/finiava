package org.dre.habittracker.domain.model;

import java.util.Arrays;

public enum Period {
    ALL,
    DAILY,
    WEEKLY,
    MONTHLY;

    // ✅ Parsing propre avec message d'erreur explicite
    public static Period from(String value) {
        try {
            return Period.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Période invalide : '" + value + "'. " +
                            "Valeurs acceptées : " + Arrays.toString(Period.values())
            );
        }
    }
}
