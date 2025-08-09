package com.dreamdev.apps.movierandomizer.enums;

import lombok.Getter;

@Getter
public enum MovieGenres {
    ACTION(1),
    ADVENTURE(2),
    ANIMATION(3),
    CHILDREN(4),
    COMEDY(5),
    CRIME(6),
    DOCUMENTARY(7),
    DRAMA(8),
    FANTASY(9),
    FILM_NOIR(10),
    HISTORY(11),
    HORROR(12),
    IMAX(13),
    MUSICAL(14),
    MYSTERY(15),
    ROMANCE(16),
    SCI_FI(17),
    THRILLER(18),
    WAR(19),
    WESTERN(20);

    private final int genreId;

    MovieGenres(int genreId) {
        this.genreId = genreId;
    }
    }
