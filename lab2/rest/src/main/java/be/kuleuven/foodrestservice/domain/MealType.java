package be.kuleuven.foodrestservice.domain;

public enum MealType {

    VEGAN("vegan"),
    VEGGIE("veggie"),
    MEAT("meat"),
    FISH("fish");
    private final String value;

    MealType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MealType fromValue(String v) {
        for (MealType c: MealType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
