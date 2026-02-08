package net.anonhub.dragonCore.engine;

import com.google.gson.annotations.SerializedName;

public class JsonConfig {
    public Difficulty difficulty;
    public TickSpeed tickSpeed;
    public Version version;
    public Actions actions;

    public static class Difficulty {
        @SerializedName("default")
        public byte defaultValue;
        public byte current;
        public byte min;
        public byte max;
    }

    public static class TickSpeed {
        @SerializedName("default")
        public byte defaultValue;
        public byte current;
    }

    public static class Version {
        public String current;
    }

    public static class Actions {
        public short[] sprint;
        public short[] moveDown;
        public short[] openTerminal;
        public short[] exit;
        public short[] moveRight;
        public short[] quit;
        public short[] moveLeft;
        public short[] moveUp;
    }
}
