package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;


public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static GuitarString[] strings;
    private static Harp[] harps;
    private static Drum[] drums;

    private static int SIZE = 37;

    private static void string() {
        strings = new GuitarString[SIZE];
        for (int i = 0; i < SIZE; i++) {
            double value = 440 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(value);
        }
    }

    private static void harp() {
        harps = new Harp[SIZE];
        for (int i = 0; i < SIZE; i++) {
            double value = 440 * Math.pow(2, (i - 24) / 12.0);
            harps[i] = new Harp(value);
        }
    }

    private static void drum() {
        drums = new Drum[SIZE];
        for (int i = 0; i < SIZE; i++) {
            double value = 440 * Math.pow(2, (i - 24) / 12.0);
            drums[i] = new Drum(value);
        }
    }

    public static void main(String[] args) {
        string();
        harp();
        drum();
        play(strings);
        //play(harps);
        //play(drums);

    }

    private static void play(GuitarString[] t) {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index >= 0) {
                    t[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString elem : t) {
                sample += elem.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString elem : t) {
                elem.tic();
            }
        }
    }
}