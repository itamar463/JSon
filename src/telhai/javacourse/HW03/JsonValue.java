package telhai.javacourse.HW03;

public interface JsonValue {
    public JsonValue get(int i) throws JsonQueryException;
    public JsonValue get(String s) throws JsonQueryException;

}
