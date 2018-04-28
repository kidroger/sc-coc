package me.shufork.common.enums;

public enum CocArmyTypeEnums {

    TROOP("troop"), SPELL("spell"), HERO("hero");

    private final String value;

    CocArmyTypeEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CocArmyTypeEnums parseOrDefault(final String value, final CocArmyTypeEnums defValue) {
        for (CocArmyTypeEnums o : CocArmyTypeEnums.values()) {
            if (o.getValue().equals(value)) {
                return o;
            }
        }
        return defValue;
    }

    public static CocArmyTypeEnums parse(final String value) throws IllegalArgumentException {
        CocArmyTypeEnums o = parseOrDefault(value, null);
        if (o == null) {
            throw new IllegalArgumentException("Invalid value : " + value);
        }
        return o;
    }

}
