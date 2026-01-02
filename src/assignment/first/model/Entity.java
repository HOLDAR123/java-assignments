package assignment.first.model;

public abstract class Entity {
    protected int id;
    private static int nextId = 1;

    public Entity() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public abstract String getDisplayName();

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
