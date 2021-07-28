package br.com.lolfood.util;

import br.com.lolfood.model.Position;

import java.time.LocalDateTime;

public final class CoordinatesUtil {

    public static Double calculateDistance(Position p1, Position p2) {

        Double x1 = p1.getLat();
        Double y1 = p1.getLon();

        Double x2 = p2.getLat();
        Double y2 = p2.getLon();

        Double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        return distance;
    }

    public static LocalDateTime calculateDueTime(Double distance) {
        return LocalDateTime.now();
    }
}
