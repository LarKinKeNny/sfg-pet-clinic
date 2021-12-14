package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity> {
    protected Map<Long, T> map = new HashMap<>();
    private Long id = 1L;

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(Long id) {
        return map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null)
                object.setId(id++);
            map.put(object.getId(), object);
        }
        else
            throw new RuntimeException("The object may not be null!");
        return object;
    }

    void deleteById(Long id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(x -> x.getValue().equals(object));
    }
}
