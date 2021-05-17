package telhai.javacourse.HW03;

public class JsonString implements JsonValue{
    private String s;
    public JsonString(){};
    public JsonString(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
    public void setS(String s) {
        this.s = s;
    }

    @Override
    public JsonValue get(int i) {
        return null;
    }

    @Override
    public JsonValue get(String str) throws JsonQueryException {
        if (s.equals(str)){
            return new JsonString(str);
        }
        throw new JsonQueryException("ERROR");
    }



    @Override
    public String toString() {
        return s;
    }
}
