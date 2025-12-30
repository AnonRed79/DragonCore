package net.anonhub.dragonCore.tagEngine;

public class Tags {
    public static final Tag ENTITY = new Tag("entity", Tag.BASE);
        public static final Tag PLAYER = new Tag("player", ENTITY);
    public static final Tag TILE = new Tag("tile", Tag.BASE);
        public static final Tag WALL = new Tag("wall", TILE);
        public static final Tag FLOOR = new Tag("floor", TILE);
        public static final Tag CEILING = new Tag("ceiling", TILE);
        public static final Tag OBJECT = new Tag("object", TILE);
}
