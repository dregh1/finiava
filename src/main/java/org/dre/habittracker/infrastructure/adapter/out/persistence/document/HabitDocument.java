package org.dre.habittracker.infrastructure.adapter.out.persistence.document;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@MongoEntity(collection = "habits")
public class HabitDocument  extends PanacheMongoEntity {
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public List<HabitCompletionDocument> completions = new ArrayList<>();
}
