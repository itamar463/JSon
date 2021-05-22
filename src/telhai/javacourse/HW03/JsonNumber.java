package telhai.javacourse.HW03;

public class JsonNumber implements JsonValue{
    private Number k;

    public JsonNumber(Number k) {
        this.k = k;
    }

    public Number getK() {
        return k;
    }

    public void setK(Number k) {
        this.k = k;
    }

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        //return a JsonNumber if the given int is equal to the JsonNumber
        if (((Number) i).equals(this.k)) return (JsonValue) this.k;
        else throw new JsonQueryException("ERROR!");
    }

    @Override
    public JsonValue get(String s) {
        return null;
    }

    @Override
    public String toString() {
        return ""+ k;
    }
}
