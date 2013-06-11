package com.mmoscene.h4j.miscellaneous;

public class Version {
    private int major, minor, revision, build;

    public String string = "";

    public Version(int major, int minor, int revision, int build) {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
        this.build = build;

        this.string = this.major + "." + this.minor + "." + this.revision + "." + this.build;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getRevision() {
        return revision;
    }

    public int getBuild() {
        return build;
    }
}
