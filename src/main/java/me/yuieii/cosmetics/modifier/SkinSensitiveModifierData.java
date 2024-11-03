package me.yuieii.cosmetics.modifier;

import java.util.Objects;

// Java language version 8 does not support records :/
// Using a final plain old class for SkinSensitiveModifierData, thanks to my IDE for generating
// boilerplate code.
public final class SkinSensitiveModifierData {
    private final boolean isApplicable;

    public SkinSensitiveModifierData(boolean isApplicable) {
        this.isApplicable = isApplicable;
    }

    public boolean isApplicable() {
        return isApplicable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        SkinSensitiveModifierData that = (SkinSensitiveModifierData) obj;
        return this.isApplicable == that.isApplicable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isApplicable);
    }

    @Override
    public String toString() {
        return "SkinSensitiveModifierData[" +
            "isApplicable=" + isApplicable + ']';
    }


}
