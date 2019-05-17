package uk.ac.cam.group2.seaspray.data;

import java.util.Set;

public enum Condition {
    CLEAR("clear.png", Set.of(113)),
    CLOUD_CLEAR("clear_cloud.png", Set.of(116)),
    CLOUD("cloud.png", Set.of(119, 122)),
    RAIN_LIGHT(
            "rain_light.png",
            Set.of(176, 179, 185, 263, 266, 293, 296, 311, 317, 323, 326, 353, 362, 368)),
    RAIN_MEDIUM("rain_medium.png", Set.of(281, 299, 302, 314, 320, 329, 332, 350, 356, 374)),
    RAIN_HEAVY("rain_heavy.png", Set.of(227, 230, 284, 305, 308, 335, 338, 359, 365, 371, 377)),
    STORM("storm.png", Set.of(200, 386, 389, 392, 395)),
    FOG("rain_medium.png", Set.of(143, 248, 260)); // Fallback to rain_medium.

    private static final String PATH_PREFIX = "src/main/resources/conditions/";
    private final String path;
    private final Set<Integer> codes;

    private Condition(String path, Set<Integer> codes) {
        this.path = PATH_PREFIX + path;
        this.codes = codes;
    }

    public boolean hasCode(int code) {
        return codes.contains(code);
    }

    public String getPath() {
        return path;
    }
}
