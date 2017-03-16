package com.perfecto.rooms;

/**
 * Created by marji on 2017-03-13.
 */

import java.awt.Point;

public interface Room {

    public Point getStartPosition();
    public boolean contains(Point point);
}
