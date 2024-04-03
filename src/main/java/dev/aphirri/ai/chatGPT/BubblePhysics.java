package dev.aphirri.ai.chatGPT;

import processing.core.*;

public class BubblePhysics extends PApplet {

    Bubble[] bubbles;

    public static void generate() {
        PApplet.main("dev.aphirri.ai.chatGPT.BubblePhysics");
    }

    public void settings() {
        size(1600, 1200);
    }

    public void setup() {
        bubbles = new Bubble[80];
        for (int i = 0; i < bubbles.length; i++) {
            bubbles[i] = new Bubble(random(width), random(height), random(20, 50));
        }
    }

    public void draw() {
        background(255);

        for (int i = 0; i < bubbles.length; i++) {
            bubbles[i].display();
            bubbles[i].update();
            bubbles[i].checkEdges();
            for (int j = i + 1; j < bubbles.length; j++) {
                bubbles[i].collide(bubbles[j]);
            }
        }
    }

    class Bubble {
        float x, y;
        float r;
        float dx, dy;

        Bubble(float x, float y, float r) {
            this.x = x;
            this.y = y;
            this.r = r;
            dx = random(-2, 2);
            dy = random(-2, 2);
        }

        void display() {
            stroke(0);
            fill(0, 100);
            ellipse(x, y, r * 2, r * 2);
        }

        void update() {
            x += dx;
            y += dy;
        }

        void checkEdges() {
            if (x + r > width) {
                x = width - r;
                dx *= -0.5F;
            } else if (x - r < 0) {
                x = r;
                dx *= -0.5F;
            }
            if (y + r > height) {
                y = height - r;
                dy *= -0.5F;
            } else if (y - r < 0) {
                y = r;
                dy *= -0.5F;
            }
        }

        void collide(Bubble other) {
            float dx = other.x - x;
            float dy = other.y - y;
            float distance = sqrt(dx*dx + dy*dy);
            if (distance < r + other.r) {
                float angle = atan2(dy, dx);
                float targetX = x + cos(angle) * (r + other.r);
                float targetY = y + sin(angle) * (r + other.r);
                float ax = (targetX - other.x) * 0.05F;
                float ay = (targetY - other.y) * 0.05F;
                dx -= ax;
                dy -= ay;
                other.dx += ax;
                other.dy += ay;
                this.dx -= ax;
                this.dy -= ay;
            }
        }
    }
}
