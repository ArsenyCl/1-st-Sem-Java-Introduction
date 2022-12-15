package expression;

import java.util.Map;

public class ExpressOperation {
    private static final Map<String, Integer> operationPriotityMap = Map.of(
            "+", 1,
            "-", 1,
            "set", 0,
            "clear", 0,
            "*", 2,
            "/", 2,
            "- ", 3,
            "count", 3
    );

    public static int getPriority(String str) {
        return operationPriotityMap.getOrDefault(str, -1);
    }
}
