package com.perfecto.rooms;

import java.awt.*;

/**
 * Created by marji on 2017-03-13.
 */
public class CircularRoom implements Room {

    Point center = new Point(0,0);
    int radius;

    Point startPosition;

    public CircularRoom(Point startPosition,int radius) {

        // validate the dimensions and the start position
        if(radius > 0 ){
            this.radius = radius;
            if(!contains(startPosition))
                throw new IllegalArgumentException("start position is out of boundary!");
            this.startPosition = startPosition;
        }else
            throw new IllegalArgumentException("Room radius is invalid!");
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public boolean contains(Point point) {

        // calculate the distance from the center and compare it to the room's radius
        double distanceFromCenter = twoPointsDistance(center,point);

        // used ceil instead of floor to avoid marginal cases
        // e.g distance 5.0002 should be beyond room's limit if the radius is 5
        // using floor would give 5.0 which means the new position is on the border which is not the real case
        if(Math.ceil(distanceFromCenter) <= radius)
            return true;

        return false;
    }

    public double twoPointsDistance(Point firstPoint, Point secondPoint){
        double xDifference = firstPoint.x - secondPoint.x;
        double yDifference = firstPoint.y - secondPoint.y;

        // calculate distance between two given points using Pythagorean Theorem
        double distance = Math.sqrt(Math.pow(xDifference,2)+Math.pow(yDifference,2));

        return distance;
    }
}
