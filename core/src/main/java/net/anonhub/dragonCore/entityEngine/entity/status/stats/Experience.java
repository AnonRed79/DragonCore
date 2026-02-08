package net.anonhub.dragonCore.entityEngine.entity.status.stats;

import net.anonhub.dragonCore.entityEngine.entity.Status;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.engine.interfaces.ITickable;

import static net.anonhub.dragonCore.engine.Settings.display;

public class Experience implements ITickable, IDisplayable {

    // TODO num * difficulty + 100
    // num is the max amount of xp that rounds out when character is maxed
    public final int experienceCap = 2016400+100;



    private final Status status;
    private final int pointsPerLevel;

    private int experience;
    private int level;
    private int tier;
    private int ascendance;

    private int levelExperience;
    private int displayLevel;
    private int displayTier;

    private int statPoints;
    public int extraStatPoints;

    private int experienceToNextLevel;
    private int experienceFromNextLevel;


    public Experience(Status status, int pointsPerLevel) {
        this.status = status;
        this.pointsPerLevel = pointsPerLevel;
        addToList();
    }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;

        if (this.experience < 0) {
            this.experience = 0;
        }
        if (this.experience > experienceCap) {
            this.experience = experienceCap;
        }
    }
    public void addExperience(int totalExperience) {
        this.experience += totalExperience;
        if (this.experience < 0) {
            this.experience = 0;
        }
        if (this.experience > experienceCap) {
            this.experience = experienceCap;
        }
    }

    public int getLevelExperience() {
        return levelExperience;
    }
    public int getExperienceFromNextLevel() {
        return experienceFromNextLevel;
    }
    public int getDisplayLevel() {
        return displayLevel;
    }
    public int getDisplayTier() {
        return displayTier;
    }
    public int getDisplayAscendance() {
        return ascendance;
    }
    public int getStatPoints() {
        return statPoints;
    }
    public int getExperienceToNextLevel() {
        if (experience == experienceCap) {
            return 0;
        }
        return experienceToNextLevel;
    }
    public int getPointsPerLevel() {
        return pointsPerLevel;
    }
    public int getLevel() {
        return level;
    }
    public int getTier() {
        return tier;
    }
    public int getAscendance() {
        return ascendance;
    }

    public String getTierName() {
        return switch(displayTier) {
            case 0 -> "Novice";
            case 1 -> "Apprentice";
            case 2 -> "Adept";
            case 3 -> "Journeyman";
            case 4 -> "Expert";
            case 5 -> "Master";
            case 6 -> "Grandmaster";
            case 7 -> "Paragon";
            default -> "" + displayTier;
        };
    }

    public String getAscendanceName() {
        return switch(ascendance) {
            case 0 -> "Mortal";
            case 1 -> "Exemplar";
            case 2 -> "Mythic";
            case 3 -> "Transcendent";
            case 4 -> "Superior";
            case 5 -> "Supreme";
            case 6 -> "Primordial";
            case 7 -> "Celestial";
            case 8 -> "Divine";
            default -> "" + ascendance;
        };

    }


    @Override
    public void tick() {
        // TODO (int) Math.sqrt((double) (experience - 100) / difficulty)
        level = (int) Math.sqrt((double) (experience - 100));
        tier = level /20;
        ascendance = tier /8;

        // TODO  (int) (difficulty * Math.pow(level+1, 2)) - (int) (difficulty * Math.pow(level, 2))
        experienceToNextLevel = (int) (Math.pow(level+1, 2)) - (int) (Math.pow(level, 2));
        // TODO (level == 0) {experienceToNextLevel=(difficulty+100);}
        if (level == 0) {experienceToNextLevel=(100);}

        // TODO (int) (difficulty*Math.pow(level, 2)+100);
        levelExperience = experience - (int) (Math.pow(level, 2)+100);
        if (level == 0) {levelExperience=experience;}

        displayLevel = level - (tier * 20);
        displayTier = tier - (ascendance * 8);
        experienceFromNextLevel = experienceToNextLevel-levelExperience;
    }

    @Override
    public String toString() {
        return "Level: " + displayLevel + ", Experience: " + levelExperience;
    }

    @Override
    public String display() {
        return
        "\n" + display.color("banana")  + getLevelExperience() + display.preset("/") + display.color("banana") + getExperienceToNextLevel() + display.preset("/") + display.color("banana") + getExperienceFromNextLevel() +
        "\n" + display.color("yellow") + "Level: " + getDisplayLevel() +
        "\n" + display.color("mint") + "Tier: " + getTierName() +
        "\n" + display.color("red") + "Ascendance: " + getAscendanceName();
    }
}
