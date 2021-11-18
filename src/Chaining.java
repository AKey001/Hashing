public class Chaining {

    public static class Node {
        private Object[] value;
        private Node next;

        public Node() {
            this.value = null;
            this.next = null;
        }

        public boolean add(Object[] value, int index) {
            if (index > 1 && this.next != null) {
                return this.next.add(value, index - 1);
            } else if (index == 1) {
                Node newNode = new Node();
                newNode.value = value;
                newNode.next = this.next;
                this.next = newNode;
                return true;
            }
            return false;
        }

    }

    public static class SinglyLinkedList {
        private Node head;
        private int length;

        public SinglyLinkedList() {
            this.head = new Node();
            this.length = 0;
        }

        public void add(Object[] value, Integer index) {
            if (index == null) {
                index = this.length;
            }

            if (index == 0) {
                Node newNode = new Node();
                newNode.value = value;
                newNode.next = this.head;
                this.head = newNode;
                this.length += 1;
            } else if (0 < index && index <= this.length) {
                if (this.head.add(value, index)) {
                    this.length += 1;
                }
            }
        }

    }

    private static int m = 1000;

    public static class Dictionary {
        private SinglyLinkedList[] table;

        public Dictionary() {
            this.table = new SinglyLinkedList[m];
            for (int i = 0; i < table.length; i++) {
                table[i] = new SinglyLinkedList();
            }
        }

        private static int hash(Key key) {
            return key.hashCode() % m;
        }

        public void insert(Key key, String value) {
            this.table[hash(key)].add(new Object[]{key, value}, null);
        }

        public Object get(Key key) {
            Node node = this.table[hash(key)].head;
            while (node != null) {
                if (node.value != null && node.value[0] == key) {
                    return node.value[1];
                }
                node = node.next;
            }
            return null;
        }

    }

    public static class Key {
        private String value;

        public Key(String value) throws Exception {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    public static void main(String[] args) throws Exception {
        Dictionary dictionary = new Dictionary();

        Key a = new Key("a");
        Key b = new Key("b");
        Key c = new Key("c");

        dictionary.insert(a, "Hashing");
        dictionary.insert(b, "Chaining");
        dictionary.insert(c, "with");

        System.out.println(dictionary.get(a));
        System.out.println(dictionary.get(c));
        System.out.println(dictionary.get(b));
    }


}
