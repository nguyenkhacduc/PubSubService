package pubsub;

public class Formula {
    private int x, y;
    private char op;
    private String topic;

    Formula(){
        x = 0;
        y = 0;
        topic = "";
    }

    Formula(int a, int b, String topic){
        x = a;
        y = b;
        this.topic = topic;
        if (topic.equalsIgnoreCase("plus")) op = '+';
        if (topic.equalsIgnoreCase("minus")) op = '-';
        if (topic.equalsIgnoreCase("multiply")) op = '*';
        if (topic.equalsIgnoreCase("divide")) op = '/';
    }

    public String Calculate(){
        if (op == '+') return (x + y) + "";
        if (op == '-') return (x - y) + "";
        if (op == '*') return (x * y) + "";
        if (op == '/'){
            if (y == 0) return "Error.";
            else return (x / y) + "";
        }
        return "Operation not found.";
    }

    public String print() {
        String result = Calculate();
        if (result.equalsIgnoreCase("Error.") || result.equalsIgnoreCase("Operation not found.")) return result;
        return x + " " + op + " " + y + " = " + result;
    }

    public String getTopic() {
        return topic;
    }
}
