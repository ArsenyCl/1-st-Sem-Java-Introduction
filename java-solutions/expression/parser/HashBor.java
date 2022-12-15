package expression.parser;

import java.util.HashSet;
import java.util.Set;

public class HashBor {
    HashSet<String> set;
    public HashBor(String ... par) {
        this.set = new HashSet<>();
        for (String each: par) {
            this.add(each);
        }
    }
    public void add(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            set.add(sb.toString());
        }
    }
    public boolean contains(String str) {
        return set.contains(str);
    }
}
