package net.anonhub.dragonCore.modules.base_module;

import net.anonhub.dragonCore.tagEngine.Tag;
import net.anonhub.dragonCore.tagEngine.Tag;

public class Tags extends net.anonhub.dragonCore.tagEngine.Tags {
/*
base
├─item
| ├─consumable
| | ├─potion
| | ├─weapon
| | ├─
| | ├─
| | ├─
| | └─
| ├─usable
| | ├─weapon
| | ├─tool
| | ├─
| | └─
| ├─wearable
| | ├─armor
| | ├─cloths
| | └─
| └─curio
|   ├─
|   └─
├─effect
| ├─instant
| | ├─positive
| | ├─negative
| | └─
| ├─duration
| | ├─positive
| | ├─negative
| | └─
| └─permanent
|   ├─positive
|   ├─negative
|   └─
├─character
| ├─player
| ├─hostile
| | ├─
| | └─
| ├─neutral
| | ├─
| | └─
| └─passive
|   ├─
|   └─
└─tile
  ├─wall
  | ├─
  | └─
  ├─floor
  | ├─
  | └─
  ├─ceiling
  | ├─
  | └─
  └─object
    ├─
    └─
*/

    public static final Tag ITEM = new Tag("item", Tag.BASE);
        public static final Tag CONSUMABLE = new Tag("consumable", ITEM);
            public static final Tag POTION = new Tag("potion", CONSUMABLE);
            public static final Tag WEAPON_C= new Tag("weapon", CONSUMABLE);
        public static final Tag USABLE = new Tag("usable", ITEM);
            public static final Tag WEAPON_U = new Tag("weapon", USABLE);
            public static final Tag TOOL = new Tag("tool", USABLE);
        public static final Tag WEARABLE = new Tag("wearable", ITEM);
            public static final Tag ARMOR = new Tag("armor", ITEM);
            public static final Tag CLOTHES = new Tag("clothes", ITEM);
        public static final Tag CURIO = new Tag("permanent", ITEM);

    public static final Tag EFFECT = new Tag("effect", Tag.BASE);
        public static final Tag INSTANT = new Tag("instant", EFFECT);
            public static final Tag POSITIVE_I = new Tag("positive", INSTANT);
            public static final Tag NEGATIVE_I = new Tag("negative", INSTANT);
        public static final Tag DURATION = new Tag("duration", EFFECT);
            public static final Tag POSITIVE_D = new Tag("positive", DURATION);
            public static final Tag NEGATIVE_D = new Tag("negative", DURATION);
        public static final Tag PERMANENT = new Tag("permanent", EFFECT);
            public static final Tag POSITIVE_P = new Tag("positive", PERMANENT);
            public static final Tag NEGATIVE_P = new Tag("negative", PERMANENT);

        public static final Tag HOSTILE = new Tag("hostile", ENTITY);
        public static final Tag NEUTRAL = new Tag("neutral", ENTITY);
        public static final Tag PASSIVE = new Tag("passive", ENTITY);


}
