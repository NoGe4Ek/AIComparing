package dev.aphirri.ai.copilot;

import processing.core.PApplet;
import processing.core.PVector;

public class BubbleSketch extends PApplet {

    class Bubble {
        PVector position;
        PVector velocity;
        float radius;

        Bubble(float x, float y, float r) {
            position = new PVector(x, y);
            velocity = PVector.random2D();
            radius = r;
        }

        void update() {
            position.add(velocity);
            // Check for collision with walls and add a buffer to prevent sticking
            if (position.x <= radius || position.x >= width - radius) {
                velocity.x *= -1;
                position.x = constrain(position.x, radius, width - radius);
            }
            if (position.y <= radius || position.y >= height - radius) {
                velocity.y *= -1;
                position.y = constrain(position.y, radius, height - radius);
            }
        }

        void display() {
            noStroke();
            fill(127, 200);
            ellipse(position.x, position.y, radius * 2, radius * 2);
        }
    }

    Bubble[] bubbles;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        bubbles = new Bubble[20];
        for (int i = 0; i < bubbles.length; i++) {
            bubbles[i] = new Bubble(random(width), random(height), random(10, 40));
        }
    }

    public void draw() {
        background(255);
        for (Bubble bubble : bubbles) {
            bubble.update();
            bubble.display();
        }
        checkCollisions();
    }

    void checkCollisions() {
        for (int i = 0; i < bubbles.length; i++) {
            for (int j = i + 1; j < bubbles.length; j++) {
                float distance = PVector.dist(bubbles[i].position, bubbles[j].position);
                if (distance < bubbles[i].radius + bubbles[j].radius) {
                    PVector collisionVector = PVector.sub(bubbles[i].position, bubbles[j].position);
                    collisionVector.normalize();
                    bubbles[i].velocity.add(collisionVector);
                    bubbles[j].velocity.sub(collisionVector);
                }
            }
        }
    }

    public static void generate() {
        PApplet.main("dev.aphirri.ai.copilot.BubbleSketch");
    }
}

