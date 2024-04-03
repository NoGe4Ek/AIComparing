package dev.aphirri.ai.chatGPT;

import processing.core.*;

public class BubblesSimulation extends PApplet {

    Bubble[] bubbles;

    public static void generate() {
        PApplet.main("dev.aphirri.ai.chatGPT.BubblesSimulation");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        bubbles = new Bubble[10];
        for (int i = 0; i < bubbles.length; i++) {
            bubbles[i] = new Bubble(random(width), random(height), random(20, 50));
        }
    }

    public void draw() {
        background(255);

        for (int i = 0; i < bubbles.length; i++) {
            bubbles[i].move();
            bubbles[i].display();

            // Check for collision with other bubbles
            for (int j = 0; j < bubbles.length; j++) {
                if (i != j && bubbles[i].intersects(bubbles[j])) {
                    bubbles[i].collide(bubbles[j]);
                }
            }

            // Check for collision with window walls
            bubbles[i].wallCollision();
        }
    }

    class Bubble {
        float x, y;
        float diameter;
        float dx, dy;
        float speed = 2;
        public int calm = 0;

        Bubble(float x, float y, float diameter) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.dx = random(-speed, speed);
            this.dy = random(-speed, speed);
        }

        void move() {
            if (calm > 0) {
                calm--;
            }
            float localDX = dx;
            localDX = localDX > 1 ? 1 : localDX;
            localDX = localDX < -1 ? -1 : localDX;
            float localDY = dy;
            localDY = localDY > 1 ? 1 : localDY;
            localDY = localDY < -1 ? -1 : localDY;

            if (x + localDX < speed || x + localDX > -speed) {
                x += localDX;
            }

            if (y + localDY < speed || y + localDY > -speed) {
                y += localDY;
            }
        }

        void display() {
            stroke(0);
            fill(0, 150);
            ellipse(x, y, diameter, diameter);
        }

        void collide(Bubble other) {
            float dx = other.x - x;
            float dy = other.y - y;
            float distance = sqrt(dx * dx + dy * dy);
            float minDist = diameter / 2 + other.diameter / 2;

            if (distance < minDist && calm < 1) {
                this.calm = 1;
                other.calm = 1;
                float angle = atan2(dy, dx);
                float targetX = x + cos(angle) * minDist;
                float targetY = y + sin(angle) * minDist;
                float ax = (targetX - other.x) * 0.05F;
                float ay = (targetY - other.y) * 0.05F;
                dx -= ax;
                dy -= ay;
                other.dx += ax;
                other.dy += ay;
                this.dx += dx * 0.05F;
                this.dy += dy * 0.05F;
            }
        }

        void wallCollision() {
            if (x + diameter / 2 >= width || x - diameter / 2 <= 0) {
                dx *= -1;
            }
            if (y + diameter / 2 >= height || y - diameter / 2 <= 0) {
                dy *= -1;
            }
        }

        boolean intersects(Bubble other) {
            float distance = dist(x, y, other.x, other.y);
            return distance < (diameter / 2 + other.diameter / 2);
        }
    }
}
