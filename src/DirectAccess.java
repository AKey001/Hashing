
public class DirectAccess {
    public static int m = 1000;

    public static class Dictionary {
        private String[] table;

        public Dictionary() {
            this.table = new String[m];
        }

        public void insert(Key key, String value) {
            this.table[key.hashCode()] = value;
        }

        public String get(Key key) {
            return this.table[key.hashCode()];
        }

        public void remove(Key key) {
            this.table[key.hashCode()] = null;
        }

    }

    public static class Key {
        private int value;

        public Key(int value) throws Exception {
            if (value >= 0 && value < m) {
                this.value = value;
            } else {
                throw new Exception("invalid value");
            }
        }

        @Override
        public int hashCode() {
            return value;
        }
    }

    public static void main(String[] args) throws Exception {
        Dictionary dictionary = new Dictionary();

        Key a = new Key(56);
        Key b = new Key(234);
        Key c = new Key(983);
        Key d = new Key(234);

        dictionary.insert(a, "Hello");
        dictionary.insert(b, "!");
        dictionary.insert(c, "World");

        System.out.println(dictionary.get(a));
        System.out.println(dictionary.get(c));
        System.out.println(dictionary.get(b));

        dictionary.insert(d, ".");

        System.out.println(dictionary.get(a));
        System.out.println(dictionary.get(c));
        System.out.println(dictionary.get(b));
        System.out.println(dictionary.get(d));

        dictionary.remove(b);
        System.out.println(dictionary.get(b));
    }

}
