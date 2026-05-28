package org.dre.habittracker.infrastructure.adapter.out.persistence;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.port.out.HabitRepository;
import org.dre.habittracker.infrastructure.adapter.out.persistence.document.HabitDocument;
import org.dre.habittracker.infrastructure.adapter.out.persistence.mapper.HabitPersistenceMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class HabitRepositoryAdapter implements HabitRepository {

    @Inject
    HabitPersistenceMapper mapper;

    @Override
    public void save(Habit habit) {
        HabitDocument document = mapper.toDocument(habit);

        if (habit.getId() != null) {
            // ← Mise à jour si l'id existe déjà
            document.id = new ObjectId(habit.getId());
            document.update();
        } else {
            // ← Création si pas d'id
            document.persist();
        }
    }

    @Override
    public Optional<Habit> findById(String id) {
        return HabitDocument.findByIdOptional(new ObjectId(id))
                .map(doc -> mapper.toDomain((HabitDocument) doc));
    }

    @Override
    public List<Habit> findAll() {
        return HabitDocument.<HabitDocument>listAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}